package com.epam.preprod.kopaniev.repository;

import com.epam.preprod.kopaniev.AppException;
import com.epam.preprod.kopaniev.bean.Order;
import com.epam.preprod.kopaniev.bean.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Vladyslav_Kopaniev on 11/25/2015.
 */
public class OrderRepositoryImpl implements OrderRepository {
    private static final String INSERT_ORDER = "INSERT INTO orders(OrderUserID, OrderAmount, OrderStatusID, OrderShipTypeID,  OrderStateDetails, OrderShipAddress) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String INSERT_ORDER_DETAIL = "INSERT INTO orderdetails(DetailOrderID, DetailPrice, DetailProductID, DetailQuantity) " +
            "VALUES (?, ?, ?, ?)";

    @Override
    public void addOrder(Order order, Connection c) throws AppException {
        try (PreparedStatement orderStm = c.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement itemStm = c.prepareStatement(INSERT_ORDER_DETAIL)) {
            orderStm.setLong(1, order.getUser().getId());
            orderStm.setFloat(2, order.getAmount());
            orderStm.setInt(3, order.getStatus().ordinal() + 1);
            orderStm.setInt(4, order.getShipType().ordinal() + 1);
            orderStm.setString(5, order.getStateDetails());
            orderStm.setString(6, order.getShipAddress());
            orderStm.executeUpdate();
            ResultSet rs = orderStm.getGeneratedKeys();
            int orderId = -1;
            if (rs.next()) {
                orderId = rs.getInt(1);
            }

            if (orderId != -1) {
                for (OrderItem item : order.getOrderedItems()) {
                    itemStm.setInt(1, orderId);
                    itemStm.setFloat(2, item.getFixedPrice());
                    itemStm.setInt(3, item.getToy().getId());
                    itemStm.setInt(4, item.getCount());
                    itemStm.addBatch();
                }
                itemStm.executeBatch();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException("Can't add new order", e);
        }
    }
}
