package ua.nure.kopaniev.repository;

import ua.nure.kopaniev.bean.Item;
import ua.nure.kopaniev.bean.QueryBean;

import java.util.List;


public interface ItemRepository {

    List<Item> getItems(QueryBean bean);

    int getCountOfItems(QueryBean bean);

    Item getItem(long id);
}
