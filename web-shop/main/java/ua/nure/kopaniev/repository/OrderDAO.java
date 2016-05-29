package ua.nure.kopaniev.repository;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ua.nure.kopaniev.bean.Order;

import java.util.Date;
import java.util.List;

@Slf4j
@Repository
public class OrderDAO implements OrderRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Override
    public void addOrders(List<Order> orders) {
        log.info("::addOrder({})", orders.toString());

        val sql = "INSERT INTO `order`(id_user, id_item, quantity,  date) " +
                "VALUES (:idUser, :idItem, :quantity, :date)";

        final Date orderDate = new Date();
        SqlParameterSource[] sps = orders.stream()
                .map(order -> addToSource(order, orderDate))
                .toArray(SqlParameterSource[]::new);

        template.batchUpdate(sql, sps);
    }

    private SqlParameterSource addToSource(Order order, Date date) {
        return new MapSqlParameterSource("idUser", order.getUserId())
                .addValue("idItem", order.getItemId())
                .addValue("quantity", order.getQuantity())
                .addValue("date", date);
    }
}
