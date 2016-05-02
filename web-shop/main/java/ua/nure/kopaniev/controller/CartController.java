package ua.nure.kopaniev.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.nure.kopaniev.cart.UserCart;
import ua.nure.kopaniev.service.items.ItemService;

@Slf4j
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserCart userCart;

    @RequestMapping
    public String getCart() {
        log.info("::getCart()");
        return "cart";
    }

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

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    private void delete(@PathVariable long id) {
        log.info("::delete({})", id);
        userCart.remove(id);
    }

    @RequestMapping(value = "/clear", method = RequestMethod.DELETE)
    private void clear() {
        log.info("::clear()");
        userCart.clear();
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public String checkout() {
        log.info("::checkout()");
        return "checkout";
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public String doCheckout() {
        log.info("::doCheckout()");

        return "checkout";
    }

}
