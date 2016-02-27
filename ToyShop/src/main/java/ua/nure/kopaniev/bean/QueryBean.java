package ua.nure.kopaniev.bean;


import java.util.Arrays;
import java.util.Map;

/**
 * Created by Vladyslav_Kopaniev on 11/20/2015.
 */
public class QueryBean {
    private float priceFrom = 0;
    private float priceTo = Float.MAX_VALUE;
    private String[] brands;
    private String[] categories;
    private String name;
    private int offset = 0;
    private int count = 3;
    private Map<SortField, String> sort;

    public QueryBean() {
    }

    public QueryBean(float priceFrom, float priceTo, String[] brands,
                     String[] categories, int offset, int count, String name, Map<SortField, String>  sort) {
        if (priceFrom >= 0) {
            this.priceFrom = priceFrom;
        }
        if (priceTo >= 1) {
            this.priceTo = priceTo;
        }
        if (offset >= 0) {
            this.offset = offset;
        }
        if (count >= 1) {
            if (count > 9) {
                this.count = 9;
            } else {
                this.count = count;
            }
        }

        this.brands = brands;
        this.categories = categories;
        this.name = name;
        this.sort = sort;
    }


    public String getName() {
        return name;
    }

    public Map<SortField, String> getSort() {
        return sort;
    }

    public int getOffset() {
        return offset;
    }

    public int getCount() {
        return count;
    }

    public float getPriceFrom() {
        return priceFrom;
    }

    public float getPriceTo() {
        return priceTo;
    }

    public String[] getBrands() {
        return brands;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setPriceFrom(float priceFrom) {
        if (priceFrom >= 0) {
            this.priceFrom = priceFrom;
        }
    }

    public void setPriceTo(float priceTo) {
        if (priceTo >= 1) {
            this.priceTo = priceTo;
        }
    }

    public void setBrands(String[] brands) {
        this.brands = brands;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public void setOffset(int offset) {
        if (offset >= 0) {
            this.offset = offset;
        }
    }

    public void setCount(int count) {
        if (count >= 1) {
            this.count = count;
        }
    }

    public void setSort(Map<SortField, String>  sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "QueryBean{" +
                "priceFrom=" + priceFrom +
                ", priceTo=" + priceTo +
                ", brands='" + Arrays.toString(brands) + '\'' +
                ", categories='" + categories + '\'' +
                ", offset=" + offset +
                ", count=" + count +
                ", sort=" + sort +
                '}';
    }
}
