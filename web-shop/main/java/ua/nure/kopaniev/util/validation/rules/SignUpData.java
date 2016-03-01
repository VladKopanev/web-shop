package ua.nure.kopaniev.util.validation.rules;

import lombok.Data;

@Data
public class SignUpData {
    
    private String name;
    private String surename;
    private String password;
    private String email;
}
