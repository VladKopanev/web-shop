package ua.nure.kopaniev.service;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ua.nure.kopaniev.bean.EmailData;
import ua.nure.kopaniev.bean.Order;
import ua.nure.kopaniev.bean.OrderItem;
import ua.nure.kopaniev.bean.User;
import ua.nure.kopaniev.cart.UserCart;
import ua.nure.kopaniev.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserCart userCart;

    @Autowired
    private RestTemplate template;

    @Autowired
    private EmailService emailService;

    @Value("${pay.system.url}")
    private String paySystemUrl;

    @Value("${this.url}")
    private String thisAppUrl;

    private static final String REFERER_HEADER_NAME = "Referer";

    private static final String CASH_PARAMETER_NAME = "cash=";

    @Transactional
    public String makeOrder() {
        User user = userService.getCurrentUser();
        long userId = user.getId();
        List<OrderItem> orderItems = userCart.getItems().parallelStream()
                .map(item -> new OrderItem(item.getId(), userCart.getCountOfItems(item)))
                .collect(Collectors.toList());
        Order order = new Order();
        order.setOrderItems(orderItems);
        order.setUserId(userId);
        asyncAddOrder(order);
        val emailData = new EmailData();
        emailData.setOrderedSum(String.valueOf(userCart.getSumOfItems()));
        emailData.setCustomerName(user.getFullname());
        String paymentSystemResponse = makePayment();
        emailService.sendNotificationToCustomer(user.getEmail(), emailData);
        emailService.sendNotificationToManager(emailData);
        return paymentSystemResponse;
    }

    @Async
    private void asyncAddOrder(Order order) {
        repository.addOrder(order);
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
