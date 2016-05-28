package ua.nure.kopaniev.email;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.TemplateProcessingParameters;
import org.thymeleaf.resourceresolver.IResourceResolver;
import org.thymeleaf.templateresolver.TemplateResolver;
import ua.nure.kopaniev.bean.EmailText;
import ua.nure.kopaniev.repository.SendMailDAO;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashSet;

@Slf4j
public class DbTemplateResolver extends TemplateResolver {

    @Autowired
    private SendMailDAO dao;

    public DbTemplateResolver() {
        setResourceResolver(new DbResourceResolver());
        HashSet<String> p = new HashSet<>();
        p.add("*");
        setResolvablePatterns(p);
    }

    @Override
    protected String computeResourceName(TemplateProcessingParameters params) {
        String templateName = params.getTemplateName();
        log.debug("Template Resource Name: " + templateName);
        return templateName;
    }

    private class DbResourceResolver implements IResourceResolver {

        @Override
        public InputStream getResourceAsStream(TemplateProcessingParameters params, String resourceName) {

            EmailText template;
            try {
                template = dao.getTemplateByType(resourceName);
                if(template == null){
                    log.error("Template not found for resourceName: " + resourceName);
                }
                log.trace("Template: " + template);

                if (template != null && !template.getEmailText().isEmpty()) {
                    return new ByteArrayInputStream(template.getEmailText().getBytes());
                }
            } catch (Exception e) {
                log.error("Some exception occurred when geting email template", e);
            }
            return null;
        }

        @Override
        public String getName() {
            return "dbResourceResolver";
        }
    }
}
