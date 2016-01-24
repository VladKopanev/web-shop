package com.epam.preprod.kopaniev.bean;

/**
 * Created by Vladyslav_Kopaniev on 11/17/2015.
 */
public class Option {
    String group;
    String name;

    public Option(String group, String name) {
        this.group = group;
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Option{" +
                "group='" + group + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
