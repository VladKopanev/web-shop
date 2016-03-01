package ua.nure.kopaniev.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.nure.kopaniev.util.AppException;
import ua.nure.kopaniev.util.Path;
import ua.nure.kopaniev.cart.UserCart;
import ua.nure.kopaniev.cart.UserCartImpl;
import ua.nure.kopaniev.service.items.ItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;

@Slf4j
@Controller
@RequestMapping("/cart")
public class CartServlet {

    @Autowired
    private ItemService itemService;

    @Autowired
    UserCart userCart;

    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
    private void add(@PathVariable long id) {
        log.info("::add({})", id);
        userCart.add(itemService.getItem(id));
    }

    @RequestMapping(value = "/countUp/{id}", method = RequestMethod.PUT)
    private void countUp(@PathVariable long id) {
        log.info("::countUp({})", id);
        userCart.increase(id);
    }

    @RequestMapping(value = "/countDown/{id}", method = RequestMethod.DELETE)
    private void countDown(@PathVariable long id) {
        log.info("::countDown({})", id);
        userCart.decrease(id);
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    private void remove(@PathVariable long id) {
        log.info("::remove({})", id);
        userCart.decrease(id);
    }

    @RequestMapping(value = "/clear", method = RequestMethod.DELETE)
    private void clear() {
        log.info("::clear()");
        userCart.clear();
    }

    @RequestMapping(value = "/checkoutPage", method = RequestMethod.GET)
    protected String checkout()  {
        log.info("::checkout()");
        return "checkout";
    }
}
