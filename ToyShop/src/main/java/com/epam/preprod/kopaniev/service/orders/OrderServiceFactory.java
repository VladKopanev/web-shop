package com.epam.preprod.kopaniev.service.orders;

import javax.servlet.ServletContext;

/**
 * Created by Vladyslav_Kopaniev on 11/25/2015.
 */
public interface OrderServiceFactory {
    OrderService getOrderService(ServletContext context);
}
