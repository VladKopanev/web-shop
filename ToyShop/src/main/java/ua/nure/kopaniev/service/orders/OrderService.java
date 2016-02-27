package ua.nure.kopaniev.service.orders;

import ua.nure.kopaniev.AppException;
import ua.nure.kopaniev.bean.Order;
import ua.nure.kopaniev.cart.UserCart;

/**
 * Created by Vladyslav_Kopaniev on 11/25/2015.
 */
public interface OrderService {
    void makeOrder(UserCart cart, Order order) throws AppException;
}
