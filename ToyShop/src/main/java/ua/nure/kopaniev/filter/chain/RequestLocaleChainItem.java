package ua.nure.kopaniev.filter.chain;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Vladyslav_Kopaniev on 11/26/2015.
 */
public class RequestLocaleChainItem extends LocaleChainItem {
    private Map<String, Locale> available;

    public RequestLocaleChainItem(Locale defaultLocale, Map<String, Locale> available) {
        super(defaultLocale);
        this.available = available;
    }

    @Override
    public Locale getLocale(HttpServletRequest req) {
        String lang = req.getParameter("lang");
        Locale requestLocale = (lang == null ? null : available.get(lang));
        return Optional.ofNullable(requestLocale)
                        .orElseGet(() -> defaultHandler.apply(req));
    }


}
