package ua.nure.kopaniev.repository;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.nure.kopaniev.util.AppException;
import ua.nure.kopaniev.bean.Item;
import ua.nure.kopaniev.bean.QueryBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class ItemDAO implements ItemRepository {

    @Autowired
    NamedParameterJdbcTemplate template;

    @Override
    public List<Item> getItems(QueryBean bean) throws AppException {

        log.info("::getItems({})", bean);

        val sql = "SELECT *" +
                "FROM item " +
                "WHERE price BETWEEN :priceFrom AND :priceTo " +
                "AND publisher IN (:publisher) " +
                "AND name LIKE (:publisher) " +
                "AND author LIKE (:author) " +
                "LIMIT :count " +
                "OFFSET :offset";

        val params = new HashMap<String, Object>();

        params.put("priceFrom", bean.getPriceFrom());
        params.put("priceTo", bean.getPriceTo());
        params.put("publisher", bean.getPublishers());
        params.put("name", bean.getName());
        params.put("author", bean.getAuthor());
        params.put("count", bean.getCount());
        params.put("offset", bean.getOffset());

        return template.query(sql, params, this::toItem);
    }

    @Override
    public int getCountOfItems(QueryBean bean) throws AppException {
        log.info("::getCountOfItems({})", bean);

        val sql = "SELECT COUNT(*)" +
                "FROM item " +
                "WHERE price BETWEEN :priceFrom AND :priceTo " +
                "AND publisher IN (:publisher) " +
                "AND name LIKE (:publisher) " +
                "AND author LIKE (:author) " +
                "LIMIT :count " +
                "OFFSET :offset";

        val params = new HashMap<String, Object>();

        params.put("priceFrom", bean.getPriceFrom());
        params.put("priceTo", bean.getPriceTo());
        params.put("publisher", bean.getPublishers());
        params.put("name", bean.getName());
        params.put("author", bean.getAuthor());
        params.put("count", bean.getCount());
        params.put("offset", bean.getOffset());

        return template.queryForObject(sql, params, Integer.class);
    }

    @Override
    public Item getItem(long id) throws AppException {
        log.info("::getItem({})", id);

        val sql = "SELECT * FROM item WHERE id = " + id;
        return template.queryForObject(sql, (Map<String, ?>)null, this::toItem);
    }

    private Item toItem(ResultSet rs, int rowNum) throws SQLException {
        val item = new Item();

        item.setId(rs.getInt("id"));
        item.setISBN(rs.getString("ISBN"));
        item.setName(rs.getString("name"));
        item.setAuthor(rs.getString("author"));
        item.setPublisher(rs.getString("publisher"));
        item.setCount(rs.getInt("count"));
        item.setPages(rs.getInt("pages"));
        item.setPrice(rs.getFloat("price"));
        item.setYear(rs.getDate("year"));
        return item;
    }
}
