package ua.nure.kopaniev.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @SneakyThrows(IOException.class)
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    protected byte[] drawCaptcha(@ModelAttribute String captchaCode) {

       return captchaService.drawCaptcha(captchaCode);
    }

}
