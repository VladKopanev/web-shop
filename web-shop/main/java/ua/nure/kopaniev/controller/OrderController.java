package ua.nure.kopaniev.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import ua.nure.kopaniev.bean.User;
import ua.nure.kopaniev.cart.UserCart;
import ua.nure.kopaniev.service.orders.OrderService;

@Slf4j
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Value("pay.system.url")
    private String PAY_SYSTEM_URL;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String makeOrder(@ModelAttribute User user, @ModelAttribute UserCart userCart,
                            String shipType, String shipAddress) {
        log.info("::makeOrder({}, {}, {}, {})", user, userCart, shipType, shipAddress);
        orderService.makeOrder();

        return new RestTemplate().postForObject(PAY_SYSTEM_URL, new HttpEntity<>(userCart.getSumOfItems()), String.class);
    }
}
