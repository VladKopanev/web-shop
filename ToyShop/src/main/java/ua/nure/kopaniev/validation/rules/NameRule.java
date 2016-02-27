package ua.nure.kopaniev.validation.rules;

import java.util.regex.Pattern;

/**
 * Created by ���� on 03.11.2015.
 */
public class NameRule implements ValidationRule {
    private static final Pattern nameRegExp = Pattern.compile("[a-zA-Z0-9_-]{3,16}$");

    @Override
    public boolean validate(SignUpData rd) {
        return rd.getName()!= null && nameRegExp.matcher(rd.getName()).matches();
    }
}
