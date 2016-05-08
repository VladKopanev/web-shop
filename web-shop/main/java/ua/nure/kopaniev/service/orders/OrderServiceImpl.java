package ua.nure.kopaniev.service.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ua.nure.kopaniev.bean.Order;
import ua.nure.kopaniev.cart.UserCart;
import ua.nure.kopaniev.repository.OrderRepository;
import ua.nure.kopaniev.service.user.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserCart userCart;

    @Autowired
    private RestTemplate template;

    @Value("${pay.system.url}")
    private String paySystemUrl;

    @Value("${this.url}")
    private String thisAppUrl;

    private static final String REFERER_HEADER_NAME = "Referer";

    private static final String CASH_PARAMETER_NAME = "cash=";

    @Override
    @Transactional
    public String makeOrder() {
        long userId = userService.getCurrentUser().getId();
        List<Order> orders = userCart.getItems().stream()
                .map(item -> new Order(userId, item.getId(), userCart.getCountOfItems(item)))
                .collect(Collectors.toList());
        repository.addOrders(orders);
        return makePayment();
    }

    //returns response from payment system
    private String makePayment() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        requestHeaders.add(REFERER_HEADER_NAME, thisAppUrl);
        HttpEntity<String> request = new HttpEntity<>(CASH_PARAMETER_NAME + userCart.getSumOfItems(), requestHeaders);
        return template.postForObject(paySystemUrl, request, String.class);
    }
}
