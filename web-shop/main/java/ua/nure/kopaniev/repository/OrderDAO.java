package ua.nure.kopaniev.repository;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.nure.kopaniev.bean.Order;
import ua.nure.kopaniev.bean.OrderItem;

import java.util.Date;

@Slf4j
@Repository
public class OrderDAO implements OrderRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final String INSERT_ORDER = "INSERT INTO `order`(id_user, date) VALUES (:idUser, :date)";
    private static final String INSERT_ORDER_ITEM = "INSERT INTO order_item(order_id, item_id, quantity) " +
                                                    "VALUES (:orderId, :itemId, :quantity)";

    @Override
    public void addOrder(Order order) {
        log.info("::addOrder({})", order.getOrderItems());
        val orderDate = new Date();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(INSERT_ORDER, new MapSqlParameterSource("idUser", order.getUserId())
                .addValue("date", orderDate), keyHolder);
        long orderId = (long)keyHolder.getKey();

        SqlParameterSource[] params = order.getOrderItems().stream()
                .map(orderItem -> addToSource(orderItem, orderId))
                .toArray(SqlParameterSource[]::new);

        template.batchUpdate(INSERT_ORDER_ITEM, params);
    }

    private SqlParameterSource addToSource(OrderItem orderItem, long orderId) {
        return new MapSqlParameterSource("orderId", orderId)
                .addValue("itemId", orderItem.getItemId())
                .addValue("quantity", orderItem.getQuantity());
    }
}
