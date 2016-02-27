package ua.nure.kopaniev.service.orders;

import ua.nure.kopaniev.AppException;
import ua.nure.kopaniev.bean.Order;
import ua.nure.kopaniev.bean.OrderItem;
import ua.nure.kopaniev.cart.UserCart;
import ua.nure.kopaniev.repository.OrderRepository;
import ua.nure.kopaniev.repository.transaction.TransactionManager;
import ua.nure.kopaniev.repository.transaction.TransactionManagerImpl;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Vladyslav_Kopaniev on 11/25/2015.
 */
public class OrderServiceImpl implements OrderService {
    private OrderRepository repository;
    private TransactionManager manager;

    public OrderServiceImpl(OrderRepository repository, ServletContext context) {
        this.repository = repository;
        manager = (TransactionManagerImpl) (context.getAttribute("transactionManager"));
    }

    @Override
    public void makeOrder(UserCart cart, Order order) throws AppException {
        List<OrderItem> items = cart.getItems().stream()
                .map(cItem ->
                        new OrderItem(cItem, cart.getCountOfItems(cItem), cItem.getPrice()))
                .collect(Collectors.toList());
        order.setOrderedItems(items);

        manager.executeTransaction(c -> {
            repository.addOrder(order, c);
            return null;
        });
    }

}
