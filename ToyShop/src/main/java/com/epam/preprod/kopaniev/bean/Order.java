package com.epam.preprod.kopaniev.bean;


import java.util.Date;
import java.util.List;

/**
 * Created by Vladyslav_Kopaniev on 11/25/2015.
 */
public class Order {
    private long id;
    private OrderStatus status = OrderStatus.ACCEPTED;
    private String stateDetails = "";
    private String shipAddress = "";
    private Date timestamp;
    private User user;
    private ShipType shipType = ShipType.DELIVERY;
    private Float amount;
    private List<OrderItem> orderedItems;

    public Order(User user, ShipType shipType, Float amount, String shipAddress, List<OrderItem> orderedItems) {
        this.user = user;
        this.shipType = shipType;
        this.amount = amount;
        this.orderedItems = orderedItems;
        this.shipAddress = shipAddress;
    }

    public Order(long id, OrderStatus status, String stateDetails, Date timestamp, User user, ShipType shipType, float amount, List<OrderItem> orderedItems) {
        this.id = id;
        this.status = status;
        this.stateDetails = stateDetails;
        this.timestamp = timestamp;
        this.user = user;
        this.shipType = shipType;
        this.orderedItems = orderedItems;
        this.amount = amount;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public Float getAmount() {
        return amount;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setStateDetails(String stateDetails) {
        this.stateDetails = stateDetails;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public void setOrderedItems(List<OrderItem> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public long getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getStateDetails() {
        return stateDetails;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public User getUser() {
        return user;
    }

    public List<OrderItem> getOrderedItems() {
        return orderedItems;
    }
}
