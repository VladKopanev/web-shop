package ua.nure.kopaniev.service.items;

import ua.nure.kopaniev.AppException;
import ua.nure.kopaniev.bean.QueryBean;
import ua.nure.kopaniev.bean.Toy;

import java.util.List;

/**
 * Created by Vladyslav_Kopaniev on 11/18/2015.
 */
public interface ItemService {
    List<Toy> getItems(QueryBean bean) throws AppException;
    int getCountOfItems(QueryBean bean) throws AppException;
    Toy getItem(int id) throws AppException;
}
