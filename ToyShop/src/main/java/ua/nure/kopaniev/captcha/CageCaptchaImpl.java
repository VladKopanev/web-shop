package ua.nure.kopaniev.captcha;

import com.github.cage.Cage;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * Created by ���� on 08.11.2015.
 */
public class CageCaptchaImpl implements Captcha {
    private final Cage CAGE = new Cage();

    @Override
    public String generateCaptchaCode() {
        Random rand = new Random(System.currentTimeMillis());
        return String.valueOf(rand.nextInt(10000) + 1000);
    }

    @Override
    public void drawCaptcha(OutputStream out, String code) throws IOException {
        CAGE.draw(code, out);
    }

}
