package com.epam.preprod.kopaniev.service.orders;

import com.epam.preprod.kopaniev.repository.OrderRepositoryImpl;

import javax.servlet.ServletContext;

/**
 * Created by Vladyslav_Kopaniev on 11/25/2015.
 */
public class OrderServiceFactoryImpl implements OrderServiceFactory {
    @Override
    public OrderService getOrderService(ServletContext context) {
        return new OrderServiceImpl(new OrderRepositoryImpl(), context);
    }
}
