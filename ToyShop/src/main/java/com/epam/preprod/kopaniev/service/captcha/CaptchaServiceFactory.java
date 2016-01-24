package com.epam.preprod.kopaniev.service.captcha;

/**
 * Created by Влад on 08.11.2015.
 */
public interface CaptchaServiceFactory {
    CaptchaService getCaptchaService(String captchaSavingMode);
}
