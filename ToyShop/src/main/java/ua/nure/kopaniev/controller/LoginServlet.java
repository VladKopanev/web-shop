package ua.nure.kopaniev.controller;

import ua.nure.kopaniev.AppException;
import ua.nure.kopaniev.Path;
import ua.nure.kopaniev.bean.User;
import ua.nure.kopaniev.service.ServiceFactories;
import ua.nure.kopaniev.service.user.UserService;
import ua.nure.kopaniev.service.user.UserServiceFactory;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login.do/*")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServiceFactories factories = (ServiceFactories) req.getServletContext().getAttribute("factories");
        UserServiceFactory userServiceFactory = factories.getUserServiceFactory();

        UserService userService = userServiceFactory.getUserService(req.getServletContext());
        String email = req.getParameter("email");
        String pass = req.getParameter("pass");
        User user = null;
        try {
            user = userService.getUser(email);
        } catch (AppException e) {
            e.printStackTrace();
        }

        if (user == null || !user.getPass().equals(pass)) {
            req.setAttribute("nonValidLoginInfo", true);
            req.getRequestDispatcher(Path.LOGIN_PAGE).forward(req, resp);
        } else {
            req.getSession().setAttribute("user", user);
            String referrer = StringUtils
                    .isNoneEmpty(req.getHeader("referer"))
                    ? req.getHeader("referer") : Path.HOME;

            resp.sendRedirect(referrer);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
