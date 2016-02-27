package ua.nure.kopaniev.validation.rules;

import java.util.regex.Pattern;

/**
 * Created by ���� on 03.11.2015.
 */
public class SurnameRule implements ValidationRule {
    private static final Pattern surnameRegExp = Pattern.compile("^[a-zA-Z]{2,100}$");

    @Override
    public boolean validate(SignUpData rd) {
        return rd.getSurename()!= null && surnameRegExp.matcher(rd.getSurename()).matches();
    }
}
