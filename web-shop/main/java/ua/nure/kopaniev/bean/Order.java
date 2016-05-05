package ua.nure.kopaniev.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Order info bean.
 */
@Data
public class Order implements Serializable {
    private long id;
    private long userId;
    private long itemId;
    private int quantity;

    public Order(long userId, long itemId, int quantity) {
        this.userId = userId;
        this.itemId = itemId;
        this.quantity = quantity;
    }
}
