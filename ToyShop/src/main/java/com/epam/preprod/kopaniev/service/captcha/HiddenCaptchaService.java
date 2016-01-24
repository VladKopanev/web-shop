package com.epam.preprod.kopaniev.service.captcha;

import com.epam.preprod.kopaniev.captcha.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 * Created by Vladyslav_Kopaniev on 11/16/2015.
 */
public class HiddenCaptchaService extends ContextCaptchaService {

    public HiddenCaptchaService(Captcha captcha) {
        super(captcha);
    }

    @Override
    public void setNewCaptchaCode(HttpServletRequest req, HttpServletResponse resp) {
        Random rand = new Random(System.currentTimeMillis());
        String captchaId = String.valueOf(System.nanoTime() + (rand.nextInt(2000) - 1000));
        req.getServletContext().setAttribute(captchaId, captcha.generateCaptchaCode());
        req.setAttribute("captchaId", captchaId);
        System.out.println("Captcha id" + captchaId);
    }

    @Override
    public boolean checkCaptcha(HttpServletRequest req) {
        Object time = req.getSession().getAttribute("startRgsrtTime");
        if (time == null) {
            return false;
        }
        long startRgstrTime = (long) time;

        String userCode = req.getParameter("user_code");
        String captchaId = req.getParameter("captchaId");

        System.out.println("Captcha id " + captchaId);
        String captchaCode = "";
        if (!"".equals(captchaId)) {
            captchaCode = (String) req.getServletContext().getAttribute(captchaId);
        }
        System.out.println(captchaCode + " " + userCode);

        return !("".equals(captchaCode) || "".equals(userCode) || !captchaCode.equals(userCode)
                || !checkTimeOut(startRgstrTime));
    }
}
