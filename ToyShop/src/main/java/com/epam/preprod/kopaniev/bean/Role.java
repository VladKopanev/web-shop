package com.epam.preprod.kopaniev.bean;

/**
 * Created by Влад on 28.11.2015.
 */
public enum Role {
    USER, ADMIN, OTHER;


    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
