package ua.nure.kopaniev.validation.rules;

/**
 * Created by ���� on 03.11.2015.
 */
public class SignUpData {
    
    private String name;
    private String surename;
    private String password;
    private String email;

    public SignUpData() {
    }

    public SignUpData(String name, String surename, String password, String email) {
        this.name = name;
        this.surename = surename;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getSurename() {
        return surename;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
