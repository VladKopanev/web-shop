package ua.nure.kopaniev.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderItem implements Serializable {
    private long id;
    private long itemId;
    private long orderId;
    private int quantity;

    public OrderItem(long itemId, int quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }
}
