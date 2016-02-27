package ua.nure.kopaniev.util.validation;

import ua.nure.kopaniev.util.validation.rules.ValidationRule;

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
