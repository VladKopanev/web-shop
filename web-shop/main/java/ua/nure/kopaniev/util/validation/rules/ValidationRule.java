package ua.nure.kopaniev.util.validation.rules;

/**
 * Represents validation rule for special field
 * */
public interface ValidationRule {
    /**
     * @return true if data is valid
     * */
    boolean validate(SignUpData data);
}
