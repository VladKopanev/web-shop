package ua.nure.kopaniev.captcha;

import java.io.IOException;
import java.io.OutputStream;

public interface Captcha {
    String generateCaptchaCode();

    byte[] drawCaptcha(String code) throws IOException;
}
