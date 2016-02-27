package ua.nure.kopaniev.controller;

import ua.nure.kopaniev.util.Path;
import ua.nure.kopaniev.service.captcha.CaptchaService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/signupPage.do")
public class SignUpPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServiceFactories factories = (ServiceFactories) req.getServletContext().getAttribute("factories");
        CaptchaServiceFactory captchaServiceFactory = factories.getCaptchaServiceFactory();
        String codeSavingMode = req.getServletContext().getInitParameter("savingMode");
        CaptchaService captchaService = captchaServiceFactory.getCaptchaService(codeSavingMode);
        captchaService.setNewCaptchaCode(req, resp);
        req.getRequestDispatcher(Path.SIGN_UP_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
