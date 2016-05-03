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

    public Order(long userId, long itemId) {
        this.userId = userId;
        this.itemId = itemId;
    }
}
