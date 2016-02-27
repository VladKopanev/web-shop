package ua.nure.kopaniev.bean;

/**
 * Created by ���� on 28.11.2015.
 */
public enum Role {
    USER, ADMIN, OTHER;


    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
