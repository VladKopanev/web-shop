package com.epam.preprod.kopaniev.service;

import com.epam.preprod.kopaniev.service.captcha.CageCaptchaServiceFactoryImpl;
import com.epam.preprod.kopaniev.service.captcha.CaptchaServiceFactory;
import com.epam.preprod.kopaniev.service.items.ItemServiceFactory;
import com.epam.preprod.kopaniev.service.items.ItemServiceFactoryImpl;
import com.epam.preprod.kopaniev.service.orders.OrderServiceFactory;
import com.epam.preprod.kopaniev.service.orders.OrderServiceFactoryImpl;
import com.epam.preprod.kopaniev.service.user.UserServiceFactory;
import com.epam.preprod.kopaniev.service.user.UserServiceFactoryImpl;

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
