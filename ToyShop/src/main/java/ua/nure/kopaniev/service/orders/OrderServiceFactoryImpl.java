package ua.nure.kopaniev.service.orders;

import ua.nure.kopaniev.repository.OrderRepositoryImpl;

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
