package ua.nure.kopaniev.filter.chain;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;

/**
 * Created by Vladyslav_Kopaniev on 11/27/2015.
 */
public class CookieLocaleChainItem extends LocaleChainItem {

    public CookieLocaleChainItem(Locale defaultLocale) {
        super(defaultLocale);
    }

    @Override
    public Locale getLocale(HttpServletRequest req) {
        final String localeName = "lang";
        String cookieLocaleName = null;
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (localeName.equals(cookie.getName())) {
                cookieLocaleName = cookie.getValue();
            }
        }
        Locale cookieLocale = cookieLocaleName == null ? null : new Locale(cookieLocaleName);
        return Optional.ofNullable(cookieLocale).orElseGet(() -> defaultHandler.apply(req));
    }
}
