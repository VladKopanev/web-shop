package ua.nure.kopaniev.bean;

/**
 * Created by Vladyslav_Kopaniev on 11/20/2015.
 */
public enum SortField {
    NAME("ProductName"),
    PRICE("ProductPrice");

    private String dbFieldName;

    SortField(String dbFieldName) {
        this.dbFieldName = dbFieldName;
    }

    public static SortField value(String name) {
        for (SortField sortField : values()) {
            if (sortField.dbFieldName.equals(name)) {
                return sortField;
            }
        }
        return NAME;
    }
    @Override
    public String toString() {
        return dbFieldName;
    }
}