package com.epam.preprod.kopaniev.repository;

import com.epam.preprod.kopaniev.AppException;
import com.epam.preprod.kopaniev.bean.Order;

import java.sql.Connection;

/**
 * Created by Vladyslav_Kopaniev on 11/25/2015.
 */
public interface OrderRepository {
    void addOrder(Order order, Connection c) throws AppException;
}
