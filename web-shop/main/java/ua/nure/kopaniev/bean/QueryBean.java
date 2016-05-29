package ua.nure.kopaniev.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User query bean.
 */
@Data
public class QueryBean implements Serializable {
    private double priceFrom = 0.0;
    private double priceTo = Double.MAX_VALUE;
    private List<String> publishers = new ArrayList<>();
    private String name;
    private String author;
    private long count;
    private long offset;
    private int page;
}
