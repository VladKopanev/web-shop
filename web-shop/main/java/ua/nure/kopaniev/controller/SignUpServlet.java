package ua.nure.kopaniev.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.nure.kopaniev.util.AppException;
import ua.nure.kopaniev.util.Path;
import ua.nure.kopaniev.bean.User;
import ua.nure.kopaniev.service.captcha.CaptchaService;
import ua.nure.kopaniev.service.user.UserService;
import ua.nure.kopaniev.util.validation.RulesFactoryImpl;
import ua.nure.kopaniev.util.validation.rules.SignUpData;
import ua.nure.kopaniev.util.validation.rules.ValidationRule;

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
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/signup")
public class SignUpServlet {

    @Autowired
    CaptchaService captchaService;

    @Autowired
    UserService userService;

    //TODO get ride of rules factory call
    private Map<String, ValidationRule> rules = new RulesFactoryImpl().getSignUpRules();

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    protected String getSignupPage(Model model) {
        log.info("::getSignupPage()");
        captchaService.setNewCaptchaCode(model);
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    protected ModelAndView signup(SignUpData data,
                                  @RequestParam("avatar") MultipartFile file) {

        List<String> nonValidFields = rules
                .entrySet()
                .stream()
                .filter(ruleEntry -> !ruleEntry.getValue().validate(data))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());


        if (!nonValidFields.isEmpty()) {
            return new ModelAndView("signup")
                    .addObject("nonValidFields", nonValidFields);
        }

        if (userService.getUser(data.getEmail()) != null) {
            nonValidFields.add("email");
            return new ModelAndView("signup")
                    .addObject("nonValidFields", nonValidFields);
        }


        if (!captchaService.checkCaptcha()) {
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
}
