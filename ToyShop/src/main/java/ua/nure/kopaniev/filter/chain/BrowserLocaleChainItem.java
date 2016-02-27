package ua.nure.kopaniev.filter.chain;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Vladyslav_Kopaniev on 11/26/2015.
 */
public class BrowserLocaleChainItem extends LocaleChainItem {
    private Map<String, Locale> availableLocales;

    public BrowserLocaleChainItem(Locale defaultLocale, Map<String, Locale> availableLocales) {
        super(defaultLocale);
        this.availableLocales = availableLocales;
    }

    @Override
    public Locale getLocale(HttpServletRequest req) {
        Enumeration<Locale> locales = req.getLocales();
        Locale result = null;
        while (locales.hasMoreElements()) {
            Locale nextLocale = locales.nextElement();
            System.out.print(" " + nextLocale.getLanguage() +" ");
            if (availableLocales.containsValue(nextLocale)) {
                result = nextLocale;

            } else {
                result = availableLocales
                        .values()
                        .stream()
                        .filter(l -> l.getLanguage().equals(nextLocale.getLanguage()))
                        .findFirst()
                        .orElseGet(() -> defaultHandler.apply(req));
            }
        }
        return result;
    }
}
