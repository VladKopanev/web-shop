package com.epam.preprod.kopaniev.bean;

/**
 * Created by Vladyslav_Kopaniev on 11/25/2015.
 */
public class OrderItem {
    private Toy toy;
    private int count;
    private Float fixedPrice;

    public OrderItem(Toy toy, int count, Float fixedPrice) {
        this.toy = toy;
        this.count = count;
        this.fixedPrice = fixedPrice;
    }

    public Toy getToy() {
        return toy;
    }

    public int getCount() {
        return count;
    }

    public Float getFixedPrice() {
        return fixedPrice;
    }
}
