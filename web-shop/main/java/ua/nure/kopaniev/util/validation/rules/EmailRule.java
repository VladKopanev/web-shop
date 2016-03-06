package ua.nure.kopaniev.util.validation.rules;

import java.util.regex.Pattern;

public class EmailRule implements ValidationRule {
    private static final Pattern emailRegExp = Pattern.compile(".+@.+\\..+");

    @Override
    public boolean validate(SignUpData rd) {
        return rd.getEmail()!= null && emailRegExp.matcher(rd.getEmail()).matches();
    }
}
