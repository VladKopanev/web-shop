package ua.nure.kopaniev.repository;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ua.nure.kopaniev.util.AppException;
import ua.nure.kopaniev.bean.Order;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class OrderDAO implements OrderRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Override
    public void addOrders(List<Order> orders) throws AppException {
        log.info("::addOrder({})", orders.toString());

        val sql = "INSERT INTO 'order'(id_user, id_item) VALUES (:id_user, :id_item)";

        SqlParameterSource[] sps = orders.stream()
                .map(this::addToSource)
                .toArray(SqlParameterSource[]::new);

        template.batchUpdate(sql, sps);
    }

    private SqlParameterSource addToSource(Order order) {
        return new MapSqlParameterSource("id_item", order.getItemId())
                .addValue("id_user",order.getUserId());
    }
}
