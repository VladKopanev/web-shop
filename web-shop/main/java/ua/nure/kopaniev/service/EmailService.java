package ua.nure.kopaniev.service;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;
import ua.nure.kopaniev.bean.EmailText;
import ua.nure.kopaniev.bean.EmailType;
import ua.nure.kopaniev.repository.SendMailDAO;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Slf4j
@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private SendMailDAO dao;

    private String viewName = EmailType.CUSTOMER_MAIL.getName();

    public void sendNotificationToCustomer(String email, Object data) throws MessagingException {
        //Prepare the evaluation context
        val ctx = new Context();
        ctx.setVariable("imageResourceName", "logo.jpg"); //Set file name so it can be referenced from html
        ctx.setVariable("emailDataObject", data);

        val et = dao.getTemplateByType(viewName);

        if(et == null){
            throw new IllegalArgumentException("No matching EMAIL_TYPE: " + viewName);
        }

        // Prepare message using a Spring helper
        val mimeMessage = this.mailSender.createMimeMessage();
        val message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true = multipart
        message.setSubject(et.getEmailSubject());
        message.setTo(email);

        // Create the HTML body using Thymeleaf
        val htmlContent = templateEngine.process(viewName, ctx);

        message.setText(htmlContent, true); // true = isHtml

        // Send mail
        this.mailSender.send(mimeMessage);
    };
}
