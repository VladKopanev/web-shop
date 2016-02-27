package ua.nure.kopaniev.util.validation;

import ua.nure.kopaniev.util.validation.rules.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ���� on 03.11.2015.
 */
public class RulesFactoryImpl implements RulesFactory {
    private final static Map<String, ValidationRule> signUpRules = new HashMap<String, ValidationRule>() {
        {
            put("name", new NameRule());
            put("surname", new SurnameRule());
            put("email", new EmailRule());
            put("password", new PasswordRule());
        }
    };


    @Override
    public Map<String, ValidationRule> getSignUpRules() {
        return signUpRules;
    }

}