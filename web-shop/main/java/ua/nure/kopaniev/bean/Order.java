package ua.nure.kopaniev.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Order info bean.
 */
@Data
public class Order implements Serializable {
    private long id;
    private long userId;
    private Date date;
    private List<OrderItem> orderItems;

    public Order(long id, long userId, Date date) {
        this.id = id;
        this.userId = userId;
        this.date = date;
    }

    public Order() {}
}
