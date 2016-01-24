package com.epam.preprod.kopaniev.servlet;

import com.epam.preprod.kopaniev.AppException;
import com.epam.preprod.kopaniev.Path;
import com.epam.preprod.kopaniev.cart.UserCart;
import com.epam.preprod.kopaniev.cart.UserCartImpl;
import com.epam.preprod.kopaniev.service.ServiceFactories;
import com.epam.preprod.kopaniev.service.items.ItemService;
import com.epam.preprod.kopaniev.service.items.ItemServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Created by Vladyslav_Kopaniev on 11/25/2015.
 */
@WebServlet("/cart.do/*")
public class CartServlet extends HttpServlet {

    Map<String, BiConsumer<UserCart, Integer>> actions = new LinkedHashMap<>();

    {
        actions.put("countUp", UserCart::increase);
        actions.put("countDown", UserCart::decrease);
        actions.put("delete", UserCart::remove);
        actions.put("clear", (c, i) -> c.clear());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServiceFactories factories = (ServiceFactories) req.getServletContext().getAttribute("factories");
        ItemServiceFactory itemServiceFactory = factories.getItemServiceFactory();
        ItemService service = itemServiceFactory.getItemService(req.getServletContext());

        UserCart cart = (UserCart) req.getSession().getAttribute("userCart");

        if (cart == null) {
            cart = new UserCartImpl();
            req.getSession().setAttribute("userCart", cart);
        }

        String action = req.getParameter("action");
        String id = req.getParameter("id");

        if (action != null && !"".equals(action)) {
            int toyId = 0;
            try {
                toyId = Integer.parseInt(id);
            } catch (NumberFormatException | NullPointerException e) {
                e.printStackTrace();
            }
            if (action.equals("add")) {
                try {
                    cart.add(service.getItem(toyId));
                } catch (AppException e) {
                    e.printStackTrace();
                }
            } else {
                actions.getOrDefault(action, (c, i) -> {
                }).accept(cart, toyId);
            }

        }

        req.getRequestDispatcher(Path.CART_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
