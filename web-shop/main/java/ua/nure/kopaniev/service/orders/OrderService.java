package ua.nure.kopaniev.service.orders;

import ua.nure.kopaniev.util.AppException;
import ua.nure.kopaniev.cart.UserCart;

/**
 * Ordering service.
 */
public interface OrderService {
    void makeOrder() throws AppException;
}
