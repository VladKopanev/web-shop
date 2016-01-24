package com.epam.preprod.kopaniev.validation.rules;

import java.util.regex.Pattern;

/**
 * Created by Влад on 03.11.2015.
 */
public class PasswordRule implements ValidationRule {
    private static final Pattern passRegExp = Pattern.compile("^[a-zA-Z]\\w{3,14}$");

    @Override
    public boolean validate(SignUpData rd) {
        return rd.getPassword()!= null && passRegExp.matcher(rd.getPassword()).matches();
    }
}
