package ua.nure.kopaniev.service.captcha;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.kopaniev.captcha.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.function.BooleanSupplier;

@Service
public class SessionCaptchaService extends CaptchaService {

    @Autowired
    Captcha captcha;

    @Override
    public void setNewCaptchaCode(HttpServletRequest req, HttpServletResponse resp) {
        val session = req.getSession();
        session.setAttribute("captcha_code", captcha.generateCaptchaCode());
    }

    @Override
    public String drawCaptcha(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        val captchaCodeAttribute = "captcha_code";
        val session = req.getSession();
        val code = (String) session.getAttribute(captchaCodeAttribute);
        captcha.drawCaptcha(resp.getOutputStream(), code);
        session.setAttribute("startRgsrtTime", System.currentTimeMillis());
        return captchaCodeAttribute;
    }

    //TODO
    @Override
    public boolean checkCaptcha(HttpServletRequest req) {
        val time = req.getSession().getAttribute("startRgsrtTime");
        if (time == null) {
            return false;
        }
        long startRgstrTime = (long) time;

        String userCode = req.getParameter("user_code");
        HttpSession session = req.getSession();
        String captchaCode = (String) session.getAttribute("captcha_code");
        System.out.println("Captcha code " + captchaCode);
        return !("".equals(captchaCode) || !captchaCode.equals(userCode)
                || !checkTimeOut(startRgstrTime));
    }

    @Override
    public void removeCaptcha(HttpServletRequest req, String id) {
        String attrName = "removeThreadStartTime";
        HttpSession session = req.getSession();
        Object start = session.getAttribute(attrName);
        session.setAttribute(attrName, System.currentTimeMillis() + (1000 * 60 * 5));

        BooleanSupplier startTime = () -> System.currentTimeMillis() >= (long)session.getAttribute(attrName);

        if (start == null) {
            System.out.println("Starting thread " + (long)session.getAttribute(attrName));
            new Thread(() -> {
                while (!startTime.getAsBoolean());

                session.removeAttribute(id);
                System.out.println("Session thread finished deleting captcha");
            }).start();
        } else {
            System.out.println((long)session.getAttribute(attrName));
        }

    }
}
