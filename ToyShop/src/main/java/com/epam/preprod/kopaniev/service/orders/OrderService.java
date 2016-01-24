package com.epam.preprod.kopaniev.service.orders;

import com.epam.preprod.kopaniev.AppException;
import com.epam.preprod.kopaniev.bean.Order;
import com.epam.preprod.kopaniev.bean.Toy;
import com.epam.preprod.kopaniev.cart.UserCart;

/**
 * Created by Vladyslav_Kopaniev on 11/25/2015.
 */
public interface OrderService {
    void makeOrder(UserCart cart, Order order) throws AppException;
}
