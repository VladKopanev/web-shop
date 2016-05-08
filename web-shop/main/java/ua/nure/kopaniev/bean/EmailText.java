package ua.nure.kopaniev.bean;

import lombok.Data;

/**
 * Email text bean.
 */
@Data
public class EmailText {
    private String emailType;
    private String emailSubject;
    private String emailText;

    public EmailText(String emailType, String emailSubject, String emailText) {
        this.emailType = emailType;
        this.emailSubject = emailSubject;
        this.emailText = emailText;
    }
}
