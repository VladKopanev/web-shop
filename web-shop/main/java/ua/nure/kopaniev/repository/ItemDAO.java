package ua.nure.kopaniev.repository;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ua.nure.kopaniev.bean.Item;
import ua.nure.kopaniev.bean.QueryBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class ItemDAO implements ItemRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final String SELECT_ALL = "SELECT *";
    private static final String SELECT_ALL_COUNT = "SELECT COUNT(*)";
    private static final String FILTERING_CONDITIONS = "FROM item " +
            "WHERE price BETWEEN :priceFrom AND :priceTo ";

    @Override
    public List<Item> getItems(QueryBean queryBean) {
        log.info("::getItems({})", queryBean);
        return template.query(makeSqlFromQueryBean(queryBean, SELECT_ALL), getParameters(queryBean), this::toItem);
    }

    @Override
    public int getCountOfItems(QueryBean queryBean) {
        log.info("::getCountOfItems({})", queryBean);
        return template.queryForObject(makeSqlFromQueryBean(queryBean, SELECT_ALL_COUNT), getParameters(queryBean), Integer.class);
    }

    @Override
    public Item getItem(long id) {
        log.info("::getItem({})", id);

        val sql = "SELECT * FROM item WHERE id = " + id;
        return template.queryForObject(sql, (Map<String, ?>) null, this::toItem);
    }

    private SqlParameterSource getParameters(QueryBean queryBean) {
        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("priceFrom", queryBean.getPriceFrom());
        source.addValue("priceTo", queryBean.getPriceTo());
        source.addValue("publishers", queryBean.getPublishers());
        source.addValue("name", queryBean.getName());
        source.addValue("author", queryBean.getAuthor());
        source.addValue("count", queryBean.getCount());
        source.addValue("offset", queryBean.getOffset());
        return source;
    }

    private String makeSqlFromQueryBean(QueryBean queryBean, String select) {
        StringBuilder sql = new StringBuilder(select).append(FILTERING_CONDITIONS);
        if (queryBean.getPublishers().size() > 0) {
            sql.append("AND publisher IN (:publishers) ");
        }
        if (StringUtils.isNoneEmpty(queryBean.getName())) {
            sql.append("AND name LIKE (:name) ");
        }
        if (StringUtils.isNoneEmpty(queryBean.getAuthor())) {
            sql.append("AND author LIKE (:author) ");
        }
        if (queryBean.getCount() > 0) {
            sql.append("LIMIT :count ");
        }

        if (queryBean.getOffset() > 0) {
            sql.append("OFFSET :offset");
        }
        return sql.toString();
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
