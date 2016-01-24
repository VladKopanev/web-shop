package com.epam.preprod.kopaniev.captcha;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Влад on 08.11.2015.
 */
public interface Captcha {
    String generateCaptchaCode();

    void drawCaptcha(OutputStream out, String code) throws IOException;

    default int getCaptchaTimeOut() {
        return 1;
    };
}
