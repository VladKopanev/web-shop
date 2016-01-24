package com.epam.preprod.kopaniev.validation.rules;

import java.util.regex.Pattern;

/**
 * Created by Влад on 03.11.2015.
 */
public class EmailRule implements ValidationRule {
    private static final Pattern emailRegExp = Pattern.compile(".+@.+\\..+");

    @Override
    public boolean validate(SignUpData rd) {
        return rd.getEmail()!= null && emailRegExp.matcher(rd.getEmail()).matches();
    }
}
