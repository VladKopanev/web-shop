package ua.nure.kopaniev.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.nure.kopaniev.service.CaptchaService;

import java.io.IOException;

@Controller
public class CaptchaController {

    @Autowired
    private CaptchaService captchaService;

    @SneakyThrows(IOException.class)
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public byte[] drawCaptcha(@ModelAttribute String captchaCode, Model model) {
        return captchaService.drawCaptcha(captchaCode, model);
    }
}
