package ua.nure.kopaniev.service.captcha;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.IOException;

@Service
public abstract class CaptchaService {

    @Value("${captcha.timeout}")
    private int timeout;

    public abstract void setNewCaptchaCode(Model model);

    public abstract byte[] drawCaptcha(String captchaCode, Model model)
            throws IOException;

    public abstract boolean checkCaptcha(Long startRgstrTime, String captchaCode, String userCode);

    public abstract void removeCaptcha(Model model);

    public boolean checkTimeOut(long startRegistrationTime) {
        long timePassed = System.currentTimeMillis() - startRegistrationTime;
        int minutesPassed = (int)(timePassed / (1000 * 60));
        return minutesPassed < timeout;
    }
}
