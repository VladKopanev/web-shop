package ua.nure.kopaniev.util.validation;

import ua.nure.kopaniev.util.validation.rules.SignUpData;
import ua.nure.kopaniev.util.validation.rules.ValidationRule;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SignupRulesContainer implements ValidationRulesContainer<SignUpData> {

    private Map<String, ValidationRule> rules;

    public void setRules(Map<String, ValidationRule> rules) {
        this.rules = rules;
    }

    @Override
    public ValidationRule getRule(String name) {
        return rules.get(name);
    }

    @Override
    public void addRule(String name, ValidationRule rule) {
        rules.put(name, rule);
    }

    @Override
    public List<String> validate(SignUpData data) {
        return rules
                .entrySet()
                .stream()
                .filter(ruleEntry -> !ruleEntry.getValue().validate(data))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
