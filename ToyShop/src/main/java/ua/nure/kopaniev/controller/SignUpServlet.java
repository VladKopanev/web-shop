package ua.nure.kopaniev.controller;

import ua.nure.kopaniev.AppException;
import ua.nure.kopaniev.Path;
import ua.nure.kopaniev.bean.User;
import ua.nure.kopaniev.service.ServiceFactories;
import ua.nure.kopaniev.service.captcha.CaptchaService;
import ua.nure.kopaniev.service.captcha.CaptchaServiceFactory;
import ua.nure.kopaniev.service.user.UserService;
import ua.nure.kopaniev.service.user.UserServiceFactory;
import ua.nure.kopaniev.validation.RulesFactoryImpl;
import ua.nure.kopaniev.validation.rules.SignUpData;
import ua.nure.kopaniev.validation.rules.ValidationRule;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/signup.do/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,               // 10MB
        maxRequestSize = 1024 * 1024 * 50)            // 50 MB
public class SignUpServlet extends HttpServlet {

    private Map<String, ValidationRule> rules = new RulesFactoryImpl().getSignUpRules();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServiceFactories factories = (ServiceFactories) req.getServletContext().getAttribute("factories");
        CaptchaServiceFactory captchaServiceFactory = factories.getCaptchaServiceFactory();
        UserServiceFactory userServiceFactory = factories.getUserServiceFactory();
        UserService userService = userServiceFactory.getUserService(req.getServletContext());

        SignUpData data;
        boolean toysInfo;
        boolean discountsInfo;

        Part p = null;
        if (req.getContentType() != null && req.getContentType().contains("multipart")) {
            p = req.getPart("avatar");
        }
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String pass = req.getParameter("pass");
        String email = req.getParameter("email");

        data = new SignUpData(name, surname, pass, email);

        toysInfo = "on".equals(req.getParameter("toysInfo"));
        discountsInfo = "on".equals(req.getParameter("discountsInfo"));


        List<String> nonValidFields = new ArrayList<>();
        for (Map.Entry<String, ValidationRule> ruleEntry : rules.entrySet()) {
            if (!ruleEntry.getValue().validate(data)) {
                nonValidFields.add(ruleEntry.getKey());
            }
        }

        if (!nonValidFields.isEmpty()) {
            req.setAttribute("nonValidFields", nonValidFields);
            req.getRequestDispatcher(Path.SIGN_UP_PAGE_SERVLET).forward(req, resp);
            return;
        }

        try {
            if (userService.getUser(data.getEmail()) != null) {
                nonValidFields.add("email");
                req.setAttribute("nonValidFields", nonValidFields);
                req.getRequestDispatcher(Path.SIGN_UP_PAGE_SERVLET).forward(req, resp);
                return;
            }
        } catch (AppException e) {
            e.printStackTrace();
        }

        String codeSavingMode = req.getServletContext().getInitParameter("savingMode");
        CaptchaService captchaService = captchaServiceFactory.getCaptchaService(codeSavingMode);
        if (!captchaService.checkCaptcha(req)) {
            nonValidFields.add("user_code");
            req.setAttribute("nonValidFields", nonValidFields);
            req.getRequestDispatcher(Path.SIGN_UP_PAGE_SERVLET).forward(req, resp);
            return;
        }

        User user = new User(data.getName(), data.getSurename(), data.getEmail(), data.getPassword(), toysInfo, discountsInfo);
        System.out.println("Register " + user);
        try {
            userService.addUser(user);
            String savePath = req.getServletContext().getInitParameter("avaDir");
            File fileSaveDir = new File(savePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdir();
            }

            if (p != null) {
                String filename = savePath + File.separator + data.getEmail();
                File ava = new File(filename);
                try (InputStream input = p.getInputStream()) {
                    Files.copy(input, ava.toPath());
                }
            }
        } catch (AppException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(Path.HOME);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
