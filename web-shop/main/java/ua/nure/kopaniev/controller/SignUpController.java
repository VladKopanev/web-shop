package ua.nure.kopaniev.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.nure.kopaniev.bean.User;
import ua.nure.kopaniev.service.captcha.CaptchaService;
import ua.nure.kopaniev.service.user.UserService;
import ua.nure.kopaniev.util.validation.ValidationRulesContainer;
import ua.nure.kopaniev.util.validation.rules.SignUpData;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/signup")
public class SignUpController {

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private UserService userService;

    @Value("${avatar.dir}")
    private String avaDir;

    @Autowired
    private ValidationRulesContainer<SignUpData> rulesContainer;

    @RequestMapping(method = RequestMethod.GET)
    public String getSignupPage(Model model) {
        log.info("::getSignupPage()");
        captchaService.setNewCaptchaCode(model);
        return "signup";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView signup(SignUpData data,
                               @ModelAttribute Long startRgstrTime,
                               @ModelAttribute String captchaCode,
                               @RequestParam("user_code") String userCode,
                               @RequestParam("avatar") MultipartFile avatar) throws IOException {
        log.info("::signup()");
        List<String> nonValidFields = rulesContainer.validate(data);
        if (!nonValidFields.isEmpty()) {
            log.error("Validation failed, causes: {}", nonValidFields);
            return new ModelAndView("signup")
                    .addObject("nonValidFields", nonValidFields);
        }
        if (userService.getUser(data.getEmail()) != null) {
            log.error("Validation failed, cause wrong email");
            nonValidFields.add("email");
            return new ModelAndView("signup")
                    .addObject("nonValidFields", nonValidFields);
        }
        if (!captchaService.checkCaptcha(startRgstrTime, captchaCode, userCode)) {
            log.error("Validation failed, cause wrong captcha code");
            nonValidFields.add("user_code");
            return new ModelAndView("signup")
                    .addObject("nonValidFields", nonValidFields);
        }
        User user = new User();
        user.setFullname(data.getName());
        user.setEmail(data.getEmail());
        user.setPassword(data.getPassword());
        log.info("Registering user {}", user);
        userService.addUser(user);
        File fileSaveDir = new File(avaDir);
        if (!fileSaveDir.exists() && !fileSaveDir.mkdir()) {
            log.info("Can't make avatars directory");
            return new ModelAndView("login");
        }
        if (avatar != null) {
            String filename = fileSaveDir.getAbsolutePath() + File.separator + user.getEmail();
            avatar.transferTo(new File(filename));

        }
        return new ModelAndView("login");
    }
}
