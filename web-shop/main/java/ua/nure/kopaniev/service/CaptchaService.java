package ua.nure.kopaniev.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ua.nure.kopaniev.captcha.Captcha;

import java.io.IOException;

@Slf4j
@Service
public class CaptchaService {

    @Autowired
    private Captcha captcha;

    @Value("${captcha.timeout}")
    private int timeout;

    public void setNewCaptchaCode(Model model) {
        model.addAttribute("captchaCode", captcha.generateCaptchaCode());
    }

    public byte[] drawCaptcha(String captchaCode, Model model) throws IOException {

        model.addAttribute("startRgstrTime", System.currentTimeMillis());
        return captcha.drawCaptcha(captchaCode);
    }

    public boolean checkCaptcha(@NonNull Long startRgstrTime, String captchaCode, String userCode) {
        log.info("Captcha code {} ", captchaCode);

        return !(StringUtils.isNotEmpty(captchaCode) || !captchaCode.equals(userCode)
                || !checkTimeOut(startRgstrTime));
    }

    private boolean checkTimeOut(long startRegistrationTime) {
        long timePassed = System.currentTimeMillis() - startRegistrationTime;
        int minutesPassed = (int) (timePassed / (1000 * 60));
        return minutesPassed < timeout;
    }
}
