package ua.nure.kopaniev.service.captcha;

import ua.nure.kopaniev.captcha.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ���� on 08.11.2015.
 */
public abstract class CaptchaService {
    protected Captcha captcha;

    public CaptchaService(Captcha captcha) {
        this.captcha = captcha;
    }

    public abstract void setNewCaptchaCode(HttpServletRequest req, HttpServletResponse resp);

    public abstract String drawCaptcha(HttpServletRequest req, HttpServletResponse resp)
            throws IOException;

    public abstract boolean checkCaptcha(HttpServletRequest req);

    public abstract void removeCaptcha(HttpServletRequest req, String id);

    protected boolean checkTimeOut(long startRegistrationTime) {
        long timePassed = System.currentTimeMillis() - startRegistrationTime;
        int minutesPassed = (int)(timePassed / (1000 * 60));
        return minutesPassed < captcha.getCaptchaTimeOut();
    }
}
