package ua.nure.kopaniev.service.captcha;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public abstract class CaptchaService {

    @Value("captcha.timeout")
    private int timeout;

    public abstract void setNewCaptchaCode(HttpServletRequest req, HttpServletResponse resp);

    public abstract String drawCaptcha(HttpServletRequest req, HttpServletResponse resp)
            throws IOException;

    public abstract boolean checkCaptcha(HttpServletRequest req);

    public abstract void removeCaptcha(HttpServletRequest req, String id);

    protected boolean checkTimeOut(long startRegistrationTime) {
        long timePassed = System.currentTimeMillis() - startRegistrationTime;
        int minutesPassed = (int)(timePassed / (1000 * 60));
        return minutesPassed < timeout;
    }
}
