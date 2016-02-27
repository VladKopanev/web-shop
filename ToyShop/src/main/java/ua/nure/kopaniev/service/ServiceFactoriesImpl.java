package ua.nure.kopaniev.service;

import ua.nure.kopaniev.service.captcha.CageCaptchaServiceFactoryImpl;
import ua.nure.kopaniev.service.captcha.CaptchaServiceFactory;
import ua.nure.kopaniev.service.items.ItemServiceFactory;
import ua.nure.kopaniev.service.items.ItemServiceFactoryImpl;
import ua.nure.kopaniev.service.orders.OrderServiceFactory;
import ua.nure.kopaniev.service.orders.OrderServiceFactoryImpl;
import ua.nure.kopaniev.service.user.UserServiceFactory;
import ua.nure.kopaniev.service.user.UserServiceFactoryImpl;

/**
 * Created by Vladyslav_Kopaniev on 11/10/2015.
 */
public class ServiceFactoriesImpl implements ServiceFactories {
    @Override
    public CaptchaServiceFactory getCaptchaServiceFactory() {
        return new CageCaptchaServiceFactoryImpl();
    }

    @Override
    public UserServiceFactory getUserServiceFactory() {
        return new UserServiceFactoryImpl();
    }

    @Override
    public ItemServiceFactory getItemServiceFactory() {
        return new ItemServiceFactoryImpl();
    }

    @Override
    public OrderServiceFactory getOrderServiceFactory() {
        return new OrderServiceFactoryImpl();
    }
}
