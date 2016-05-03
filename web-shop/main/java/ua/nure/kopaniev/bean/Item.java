package ua.nure.kopaniev.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Item info bean.
 */
@Data
public class Item implements Serializable {

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
