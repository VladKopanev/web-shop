package ua.nure.kopaniev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.nure.kopaniev.service.captcha.CaptchaService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CaptchaServlet {

    @Autowired
    CaptchaService captchaService;

    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    protected void drwCaptcha(HttpServletRequest req, HttpServletResponse resp) throws IOException {

       captchaService.drawCaptcha(req, resp);
    }

}
