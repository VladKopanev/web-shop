package ua.nure.kopaniev.controller;

import ua.nure.kopaniev.AppException;
import ua.nure.kopaniev.Path;
import ua.nure.kopaniev.cart.UserCart;
import ua.nure.kopaniev.service.ServiceFactories;
import ua.nure.kopaniev.service.orders.OrderService;
import ua.nure.kopaniev.service.orders.OrderServiceFactory;
import ua.nure.kopaniev.bean.Order;
import ua.nure.kopaniev.bean.OrderItem;
import ua.nure.kopaniev.bean.ShipType;
import ua.nure.kopaniev.bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/order.do/*")
public class OrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServiceFactories factories = (ServiceFactories) req.getServletContext().getAttribute("factories");
        OrderServiceFactory factory = factories.getOrderServiceFactory();
        OrderService orderService = factory.getOrderService(req.getServletContext());

        User user = (User) req.getSession().getAttribute("user");

        if (user == null) {
            resp.sendRedirect(Path.LOGIN_PAGE);
            return;
        }

        String shipTypeParam = req.getParameter("shipType");
        String shipAddress = req.getParameter("shipAddress");
        ShipType shipType;

        try {
            shipType = ShipType.valueOf(shipTypeParam);
        } catch (IllegalArgumentException e) {
            shipType = ShipType.MAIL;
        }

        UserCart cart = (UserCart) req.getSession().getAttribute("userCart");

        List<OrderItem> orderItems = new ArrayList<>();

        if (!cart.getItems().isEmpty()) {
            orderItems.addAll(cart.getItems()
                    .stream()
                    .map(toy -> new OrderItem(toy, cart.getCountOfItems(toy), toy.getPrice()))
                    .collect(Collectors.toList()));
        }

        Order order = new Order(user, shipType, cart.getSumOfItems(), shipAddress, orderItems);

        try {
            orderService.makeOrder(cart, order);
            cart.clear();
        } catch (AppException e) {
            resp.sendRedirect(Path.HOME);
        }
        resp.sendRedirect(Path.HOME);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
