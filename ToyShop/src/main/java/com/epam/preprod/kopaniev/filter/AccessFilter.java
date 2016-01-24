package com.epam.preprod.kopaniev.filter;

import com.epam.preprod.kopaniev.Path;
import com.epam.preprod.kopaniev.bean.Role;
import com.epam.preprod.kopaniev.bean.User;
import com.epam.preprod.kopaniev.filter.parser.AccessConfigParser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Влад on 28.11.2015.
 */
public class AccessFilter implements Filter {

    private Map<String, List<Role>> accessMap;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String pathToAccessConfig = filterConfig.getInitParameter("pathToAccessConfig");
        accessMap = new AccessConfigParser().parseAccessConfig(pathToAccessConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        StringBuffer URL = req.getRequestURL();
        String requestedURL = URL.substring(URL.lastIndexOf("/"));

        if (accessMap.containsKey(requestedURL)) {
            Role userRole = Role.OTHER;
            User user = (User) req.getSession().getAttribute("user");
            if (user != null) {
                userRole = user.getRole();
            }
            System.out.println("Requested url " + requestedURL);
            for (Map.Entry<String, List<Role>> entry : accessMap.entrySet()) {
                if (requestedURL.matches(entry.getKey()) && entry.getValue().contains(userRole)) {
                    System.out.println("Permission denied");
                    if (user == null) {
                        req.getRequestDispatcher(Path.LOGIN_PAGE).forward(req, resp);
                    } else {
                        resp.sendRedirect(Path.PERMIT_DENY);
                    }
                    return;
                }
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
