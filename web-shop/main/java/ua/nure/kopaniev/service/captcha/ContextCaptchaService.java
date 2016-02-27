package ua.nure.kopaniev.service.captcha;

import ua.nure.kopaniev.captcha.Captcha;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

/**
 * Created by ���� on 08.11.2015.
 */
public class ContextCaptchaService extends CaptchaService {

    public ContextCaptchaService(Captcha captcha) {
        super(captcha);
    }

    @Override
    public void setNewCaptchaCode(HttpServletRequest req, HttpServletResponse resp) {
        Random rand = new Random(System.currentTimeMillis());
        String captchaId = String.valueOf(System.nanoTime() + (rand.nextInt(2000) - 1000));
        req.getServletContext().setAttribute(captchaId, captcha.generateCaptchaCode());
        Cookie cookie = new Cookie("captchaId", captchaId);
        cookie.setMaxAge(60 * 5);
        resp.addCookie(cookie);
        req.setAttribute("captchaId", captchaId);
        System.out.println("Captcha id" + captchaId);
    }

    @Override
    public String drawCaptcha(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String captchaId = req.getParameter("captchaId");
        String code = (String) req.getServletContext().getAttribute(captchaId);
        captcha.drawCaptcha(resp.getOutputStream(), code);
        return captchaId;
    }

    @Override
    public boolean checkCaptcha(HttpServletRequest req) {
        Object time = req.getSession().getAttribute("startRgsrtTime");
        if (time == null) {
            return false;
        }
        long startRgstrTime = (long) time;

        String userCode = req.getParameter("user_code");
        String captchaId = "";
        for (Cookie c : req.getCookies()) {
            if ("captchaId".equals(c.getName())) {
                captchaId = c.getValue();
            }
        }
        System.out.println("Captcha id " + captchaId);
        String captchaCode = "";
        if (!"".equals(captchaId)) {
            captchaCode = (String) req.getServletContext().getAttribute(captchaId);
        }
        System.out.println(captchaCode + " " + userCode);

        return !("".equals(captchaCode) || "".equals(userCode) || !captchaCode.equals(userCode)
                || !checkTimeOut(startRgstrTime));
    }

    @Override
    public void removeCaptcha(HttpServletRequest req, String id) {
        final ServletContext context = req.getServletContext();
        new Thread(() -> {
            try {
                Thread.sleep(1000 * 60 * 5);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            context.removeAttribute(id);
            System.out.println("Context thread finished deleting captcha");
        }).start();
    }
}
