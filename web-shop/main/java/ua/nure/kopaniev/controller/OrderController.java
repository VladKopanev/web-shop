package ua.nure.kopaniev.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import ua.nure.kopaniev.bean.User;
import ua.nure.kopaniev.cart.UserCart;
import ua.nure.kopaniev.service.orders.OrderService;
import ua.nure.kopaniev.service.user.UserService;

import java.util.Arrays;

@Slf4j
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Value("${pay.system.url}")
    private String PAY_SYSTEM_URL;

    @Autowired
    private UserCart userCart;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String makeOrder(String shipType, String shipAddress) {
        //TODO do some debug cause of 400 Bad request when restClient call post
        log.info("::makeOrder({}, {}, {}, {})", userCart, shipType, shipAddress);
        orderService.makeOrder();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        requestHeaders.add("Referer", "localhost:/8080/home");
        return new RestTemplate(Arrays.asList(new FormHttpMessageConverter(), new StringHttpMessageConverter()))
                .postForObject(PAY_SYSTEM_URL,
                new HttpEntity<>(String.valueOf(userCart.getSumOfItems())
                        , requestHeaders), String.class);
    }
}
