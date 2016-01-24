package com.epam.preprod.kopaniev.service;

import com.epam.preprod.kopaniev.service.captcha.CaptchaServiceFactory;
import com.epam.preprod.kopaniev.service.items.ItemServiceFactory;
import com.epam.preprod.kopaniev.service.orders.OrderServiceFactory;
import com.epam.preprod.kopaniev.service.user.UserServiceFactory;

/**
 * Factory for different service factories.
 */
public interface ServiceFactories {
    CaptchaServiceFactory getCaptchaServiceFactory();

    UserServiceFactory getUserServiceFactory();

    ItemServiceFactory getItemServiceFactory();

    OrderServiceFactory getOrderServiceFactory();
}
