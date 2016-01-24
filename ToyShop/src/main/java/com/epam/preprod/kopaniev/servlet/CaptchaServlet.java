package com.epam.preprod.kopaniev.servlet;

import com.epam.preprod.kopaniev.service.ServiceFactories;
import com.epam.preprod.kopaniev.service.captcha.CageCaptchaServiceFactoryImpl;
import com.epam.preprod.kopaniev.service.captcha.CaptchaService;
import com.epam.preprod.kopaniev.service.captcha.CaptchaServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/captcha.do/*"},
            initParams = {
                    @WebInitParam(name = "time-out", value = "1")
            })
public class CaptchaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServiceFactories factories = (ServiceFactories) req.getServletContext().getAttribute("factories");
        CaptchaServiceFactory captchaServiceFactory = factories.getCaptchaServiceFactory();

        String codeSavingMode = req.getServletContext().getInitParameter("savingMode");
        CaptchaService captchaService = captchaServiceFactory.getCaptchaService(codeSavingMode);
        String id = captchaService.drawCaptcha(req, resp);
        req.getSession().setAttribute("startRgsrtTime", System.currentTimeMillis());
        //captchaService.removeCaptcha(req, id);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
