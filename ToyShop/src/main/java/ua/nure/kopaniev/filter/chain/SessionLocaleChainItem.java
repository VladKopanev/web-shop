package ua.nure.kopaniev.filter.chain;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;

/**
 * Created by Vladyslav_Kopaniev on 11/27/2015.
 */
public class SessionLocaleChainItem extends LocaleChainItem {
    public SessionLocaleChainItem(Locale defaultLocale) {
        super(defaultLocale);
    }

    @Override
    public Locale getLocale(HttpServletRequest req) {
        final String localeName = "lang";
        Locale sessionLocale = (Locale)  req.getSession().getAttribute(localeName);
        return Optional.ofNullable(sessionLocale).orElseGet(() -> defaultHandler.apply(req));
    }
}
