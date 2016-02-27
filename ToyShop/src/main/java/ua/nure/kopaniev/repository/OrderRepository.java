package ua.nure.kopaniev.repository;

import ua.nure.kopaniev.AppException;
import ua.nure.kopaniev.bean.Order;

import java.sql.Connection;

/**
 * Created by Vladyslav_Kopaniev on 11/25/2015.
 */
public interface OrderRepository {
    void addOrder(Order order, Connection c) throws AppException;
}
