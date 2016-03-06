package ua.nure.kopaniev.util.validation;

import ua.nure.kopaniev.util.validation.rules.ValidationRule;

import java.util.List;

public interface ValidationRulesContainer<T> {

    ValidationRule getRule(String name);

    void addRule(String name, ValidationRule rule);

    List<String> validate(T data);
}
