package ua.nure.kopaniev.filter.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

public class LocalesHttpRequestWrapper extends HttpServletRequestWrapper {

    private Locale locale;
    private List<Locale> locales = new ArrayList<>();

    public LocalesHttpRequestWrapper(HttpServletRequest request, Locale locale) {
        super(request);
        this.locale = locale;
        locales.add(locale);
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    @Override
    public Enumeration<Locale> getLocales() {
        return Collections.enumeration(locales);
    }
}
