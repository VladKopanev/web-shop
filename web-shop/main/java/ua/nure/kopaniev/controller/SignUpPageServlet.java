package ua.nure.kopaniev.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.nure.kopaniev.util.Path;
import ua.nure.kopaniev.service.captcha.CaptchaService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@Slf4j
@Controller
public class SignUpPageServlet {

    @Autowired
    CaptchaService captchaService;

    //TODO
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    protected String getSignupPage(Model model) {
        log.info("::getSignupPage()");
        captchaService.setNewCaptchaCode(model);
        return "signup";
    }
}
