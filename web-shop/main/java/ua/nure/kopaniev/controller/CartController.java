package ua.nure.kopaniev.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
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
    public ModelAndView getCart() {
        log.info("::getCart()");
        return new ModelAndView("cart").addObject("userCart", userCart);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
    private void add(@PathVariable long id) {
        log.info("::add({})", id);
        userCart.add(itemService.getItem(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/countUp/{id}", method = RequestMethod.PUT)
    private void countUp(@PathVariable long id) {
        log.info("::countUp({})", id);
        userCart.increase(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/countDown/{id}", method = RequestMethod.PUT)
    private void countDown(@PathVariable long id) {
        log.info("::countDown({})", id);
        userCart.decrease(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    private void delete(@PathVariable long id) {
        log.info("::delete({})", id);
        userCart.remove(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/clear", method = RequestMethod.DELETE)
    private void clear() {
        log.info("::clear()");
        userCart.clear();
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public ModelAndView checkout() {
        log.info("::checkout()");
        return new ModelAndView("checkout").addObject("userCart", userCart);
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public ModelAndView doCheckout() {
        log.info("::doCheckout()");
        return new ModelAndView("checkout").addObject("userCart", userCart);
    }

}
