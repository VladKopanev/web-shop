package ua.nure.kopaniev.bean;

import java.util.Arrays;

public enum EmailType {
    CUSTOMER_MAIL(1, "customer"),
    MANAGER_MAIL(2, "manager");

    private final int id;
    private final String name;

    EmailType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static EmailType getTypeByName(String resourceName) {
        for (EmailType emailType : values()) {
            if (emailType.name.equalsIgnoreCase(resourceName)) {
                return emailType;
            }
        }
        throw new IllegalArgumentException("Wrong email type name.");
    }
}
