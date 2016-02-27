package ua.nure.kopaniev.bean;

import lombok.Data;

/**
 * Order info bean.
 */
@Data
public class Order {
    private long id;
    private long userId;
    private long itemId;

    public Order(long userId, long itemId) {
        this.userId = userId;
        this.itemId = itemId;
    }
}
