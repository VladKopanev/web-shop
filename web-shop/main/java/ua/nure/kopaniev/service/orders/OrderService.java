package ua.nure.kopaniev.service.orders;

import ua.nure.kopaniev.util.AppException;

/**
 * Ordering service.
 */
public interface OrderService {
    void makeOrder() throws AppException;
}
