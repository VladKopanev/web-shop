package com.epam.preprod.kopaniev.validation;

import com.epam.preprod.kopaniev.validation.rules.ValidationRule;

import java.util.Map;

/**
 * Factory for validation rules.
 */
public interface RulesFactory {

    /**
     * Validation rules for sign up.
     * */
    Map<String, ValidationRule> getSignUpRules();
}
