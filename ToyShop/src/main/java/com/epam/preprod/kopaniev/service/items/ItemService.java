package com.epam.preprod.kopaniev.service.items;

import com.epam.preprod.kopaniev.AppException;
import com.epam.preprod.kopaniev.bean.QueryBean;
import com.epam.preprod.kopaniev.bean.Toy;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Vladyslav_Kopaniev on 11/18/2015.
 */
public interface ItemService {
    List<Toy> getItems(QueryBean bean) throws AppException;
    int getCountOfItems(QueryBean bean) throws AppException;
    Toy getItem(int id) throws AppException;
}
