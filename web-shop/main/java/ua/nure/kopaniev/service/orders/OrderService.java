package ua.nure.kopaniev.service.orders;

import ua.nure.kopaniev.util.AppException;

/**
 * Ordering service.
 */
public interface OrderService {
    /**
     * Returns string response represantation.
     * */
    String makeOrder() throws AppException;
}
