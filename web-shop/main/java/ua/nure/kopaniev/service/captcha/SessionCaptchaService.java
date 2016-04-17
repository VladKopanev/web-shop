package ua.nure.kopaniev.service.captcha;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ua.nure.kopaniev.captcha.Captcha;

import java.io.IOException;

@Slf4j
@Service
public class SessionCaptchaService extends CaptchaService {

    @Autowired
    private Captcha captcha;

    @Override
    public void setNewCaptchaCode(Model model) {
        model.addAttribute("captchaCode", captcha.generateCaptchaCode());
    }

    @Override
    public byte[] drawCaptcha(String captchaCode, Model model) throws IOException {

        model.addAttribute("startRgstrTime", System.currentTimeMillis());
        return captcha.drawCaptcha(captchaCode);
    }

    @Override
    public boolean checkCaptcha(@NonNull Long startRgstrTime, String captchaCode, String userCode) {
        log.info("Captcha code {} ", captchaCode);

        return !(StringUtils.isNotEmpty(captchaCode) || !captchaCode.equals(userCode)
                || !checkTimeOut(startRgstrTime));
    }

    @Override
    public void removeCaptcha(Model m) {
        //TODO implement logic of deletion
    }
}
