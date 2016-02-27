package ua.nure.kopaniev.service;

import ua.nure.kopaniev.service.captcha.CaptchaServiceFactory;
import ua.nure.kopaniev.service.items.ItemServiceFactory;
import ua.nure.kopaniev.service.orders.OrderServiceFactory;
import ua.nure.kopaniev.service.user.UserServiceFactory;

/**
 * Factory for different service factories.
 */
public interface ServiceFactories {
    CaptchaServiceFactory getCaptchaServiceFactory();

    UserServiceFactory getUserServiceFactory();

    ItemServiceFactory getItemServiceFactory();

    OrderServiceFactory getOrderServiceFactory();
}
