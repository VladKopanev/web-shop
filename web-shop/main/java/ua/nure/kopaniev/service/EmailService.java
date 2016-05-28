package ua.nure.kopaniev.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;
import ua.nure.kopaniev.bean.EmailData;
import ua.nure.kopaniev.bean.EmailType;
import ua.nure.kopaniev.repository.SendMailDAO;

import javax.mail.Message;
import javax.mail.MessagingException;

@Slf4j
@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private SendMailDAO dao;

    @Value("${manger.email}")
    private String managerEmail;

    @Value("${app.email.login}")
    private String appEmail;

    public void sendNotificationToCustomer(String email, Object data) {
       sendMessage(email, data, EmailType.CUSTOMER_MAIL.getName());
    };

    public void sendNotificationToManager(EmailData emailData) {
        sendMessage(managerEmail, emailData, EmailType.MANAGER_MAIL.getName());
    }

    @SneakyThrows(MessagingException.class)
    private void sendMessage(String email, Object data, String viewName) {
        //Prepare the evaluation context
        val ctx = new Context();
        ctx.setVariable("emailDataObject", data);

        val emailTemplate = dao.getTemplateByType(viewName);

        if(emailTemplate == null){
            throw new IllegalArgumentException("No matching EMAIL_TYPE: " + viewName);
        }

        val mimeMessage = this.mailSender.createMimeMessage();
        val message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        message.setSubject(emailTemplate.getEmailSubject());
        message.setTo(email);
        message.setFrom(appEmail);

        val htmlContent = templateEngine.process(viewName, ctx);

        message.setText(htmlContent, true); // true = isHtml

        // Send mail
        this.mailSender.send(mimeMessage);
    }
}
