package ua.nure.kopaniev.bean;

import lombok.Data;

import java.util.List;

/**
 * User query bean.
 */
@Data
public class QueryBean {
    private double priceFrom;
    private double priceTo;
    private List<String> publishers;
    private String name;
    private String author;
    private long count;
    private long offset;
    private int page;
}
