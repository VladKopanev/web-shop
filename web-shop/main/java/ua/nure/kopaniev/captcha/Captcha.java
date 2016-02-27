package ua.nure.kopaniev.captcha;

import java.io.IOException;
import java.io.OutputStream;

public interface Captcha {
    String generateCaptchaCode();

    void drawCaptcha(OutputStream out, String code) throws IOException;
}
