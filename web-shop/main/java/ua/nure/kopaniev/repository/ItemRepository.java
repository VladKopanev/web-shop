package ua.nure.kopaniev.repository;

import ua.nure.kopaniev.util.AppException;
import ua.nure.kopaniev.bean.Item;
import ua.nure.kopaniev.bean.QueryBean;

import java.util.List;


public interface ItemRepository {
    List<Item> getItems(QueryBean bean) throws AppException;

    int getCountOfItems(QueryBean bean) throws AppException;

    Item getItem(long id) throws AppException;
}
