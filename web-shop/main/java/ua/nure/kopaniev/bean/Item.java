package ua.nure.kopaniev.bean;

import lombok.Data;

import java.util.Date;

/**
 * Item info bean.
 */
@Data
public class Item {

    private long id;
    private String ISBN;
    private String name;
    private String author;
    private String publisher;
    private Date year;
    private int pages;
    private int count;
    private float price;
}
