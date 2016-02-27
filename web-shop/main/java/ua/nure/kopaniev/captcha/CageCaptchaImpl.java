package ua.nure.kopaniev.captcha;

import com.github.cage.Cage;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

@Service
public class CageCaptchaImpl implements Captcha {

    @Autowired
    private Cage cageCaptcha;

    @Override
    public String generateCaptchaCode() {
        val rand = new Random(System.currentTimeMillis());
        return String.valueOf(rand.nextInt(10000) + 1000);
    }

    @Override
    public void drawCaptcha(OutputStream out, String code) throws IOException {
        cageCaptcha.draw(code, out);
    }
}
