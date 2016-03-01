package ua.nure.kopaniev.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.nure.kopaniev.bean.Order;
import ua.nure.kopaniev.util.AppException;
import ua.nure.kopaniev.util.Path;
import ua.nure.kopaniev.cart.UserCart;
import ua.nure.kopaniev.service.orders.OrderService;
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

@Slf4j
@Controller
public class OrderServlet {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    protected String makeOrder(@ModelAttribute User user, @ModelAttribute UserCart userCart,
                             String shipType, String shipAddress) {
        log.info("::makeOrder({}, {}, {}, {})", user, userCart, shipType, shipAddress);
        orderService.makeOrder();

        return "index";
    }
}
