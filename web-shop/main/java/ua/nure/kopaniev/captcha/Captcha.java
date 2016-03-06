package ua.nure.kopaniev.captcha;

import java.io.IOException;

public interface Captcha {
    String generateCaptchaCode();

    byte[] drawCaptcha(String code) throws IOException;
}
