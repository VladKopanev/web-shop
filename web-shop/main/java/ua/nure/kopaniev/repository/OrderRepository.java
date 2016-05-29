package ua.nure.kopaniev.repository;

import ua.nure.kopaniev.bean.Order;

import java.util.List;

/**
 * Service for ordering items.
 */
public interface OrderRepository {
    void addOrders(List<Order> orders);
}
