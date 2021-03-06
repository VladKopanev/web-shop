package ua.nure.kopaniev.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.nure.kopaniev.cart.UserCart;
import ua.nure.kopaniev.service.OrderService;

@Slf4j
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserCart userCart;

    @ResponseBody
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String makeOrder(String shipType, String shipAddress) {
        log.info("::makeOrder({}, {}, {}, {})", userCart, shipType, shipAddress);
        return orderService.makeOrder();
    }
}
