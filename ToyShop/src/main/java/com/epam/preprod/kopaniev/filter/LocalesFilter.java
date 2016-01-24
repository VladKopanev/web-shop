package com.epam.preprod.kopaniev.filter;

import com.epam.preprod.kopaniev.filter.chain.*;
import com.epam.preprod.kopaniev.filter.wrapper.LocalesHttpRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * Created by Vladyslav_Kopaniev on 11/26/2015.
 */
public class LocalesFilter implements Filter {
    private Locale defaultLocale;
    private Map<String, Locale> availableLocales = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String defaultLocaleName = filterConfig.getInitParameter("default-locale");

        String availableLocalesNames = filterConfig.getInitParameter("available-locales");
        StringTokenizer tokenizer = new StringTokenizer(availableLocalesNames, " ");

        try {
            while (tokenizer.hasMoreElements()) {
                String lang = tokenizer.nextToken();
                availableLocales.put(lang, new Locale(lang));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        defaultLocale = new Locale(defaultLocaleName);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        LocaleChainItem localeChain = new RequestLocaleChainItem(defaultLocale, availableLocales);
                localeChain.setNext(new SessionLocaleChainItem(defaultLocale))
                .setNext(new CookieLocaleChainItem(defaultLocale))
                .setNext(new BrowserLocaleChainItem(defaultLocale, availableLocales));

        Locale locale = localeChain.getLocale((HttpServletRequest) req);
        System.out.println(locale);
        req.setAttribute("supportedLang", availableLocales.keySet());
        ((HttpServletRequest) req).getSession().setAttribute("lang", locale);
        chain.doFilter(new LocalesHttpRequestWrapper((HttpServletRequest)req, locale), resp);
    }

    @Override
    public void destroy() {
        //do nothing
    }
}
