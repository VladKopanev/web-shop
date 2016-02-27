package ua.nure.kopaniev.service.captcha;

import ua.nure.kopaniev.captcha.CageCaptchaImpl;
import ua.nure.kopaniev.captcha.Captcha;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ���� on 08.11.2015.
 */
public class CageCaptchaServiceFactoryImpl implements CaptchaServiceFactory {
    private final Map<String, CaptchaService> services = new HashMap<>();
    {
        Captcha captcha = new CageCaptchaImpl();
        services.put("session", new SessionCaptchaService(captcha));
        services.put("context", new ContextCaptchaService(captcha));
        services.put("hidden", new HiddenCaptchaService(captcha));
    }
    @Override
    public CaptchaService getCaptchaService(String captchaSavingMode) {
      return services.get(captchaSavingMode);
    }
}
