package com.epam.preprod.kopaniev.filter.chain;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.function.Function;

/**
 * Created by Vladyslav_Kopaniev on 11/26/2015.
 */
public abstract class LocaleChainItem {

    protected LocaleChainItem next;
    protected Locale defaultLocale;

    protected Function<HttpServletRequest, Locale> defaultHandler = (req) -> {
        if(next != null) return next.getLocale(req);
        else return defaultLocale;
    };

    public LocaleChainItem(Locale defaultLocale) {
        this.defaultLocale = defaultLocale;
    }

    public LocaleChainItem setNext(LocaleChainItem next) {
        this.next = next;
        return next;
    }

    abstract public Locale getLocale(HttpServletRequest req);
}
