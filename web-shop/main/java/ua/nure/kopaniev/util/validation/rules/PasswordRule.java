package ua.nure.kopaniev.util.validation.rules;

import java.util.regex.Pattern;

public class PasswordRule implements ValidationRule {
    private static final Pattern passRegExp = Pattern.compile("^[a-zA-Z]\\w{3,14}$");

    @Override
    public boolean validate(SignUpData rd) {
        return rd.getPassword() != null && passRegExp.matcher(rd.getPassword()).matches();
    }
}
