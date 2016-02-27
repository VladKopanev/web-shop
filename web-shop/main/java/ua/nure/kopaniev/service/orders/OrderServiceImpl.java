package ua.nure.kopaniev.service.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.kopaniev.util.AppException;
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
    UserCart cart;

    @Override
    @Transactional
    public void makeOrder() throws AppException {
        long userId = userService.getCurrentUser().getId();

        List<Order> orders = cart.getItems().stream()
                .map(i -> new Order(userId, i.getId()))
                .collect(Collectors.toList());

        repository.addOrders(orders);
    }

}
