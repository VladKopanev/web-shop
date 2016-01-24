package com.epam.preprod.kopaniev.repository;

import com.epam.preprod.kopaniev.AppException;
import com.epam.preprod.kopaniev.bean.QueryBean;
import com.epam.preprod.kopaniev.bean.Toy;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Vladyslav_Kopaniev on 11/11/2015.
 */
public interface ItemRepository {
    List<Toy> getItems(Connection c, QueryBean bean) throws AppException;
    int getCountOfItems(Connection c, QueryBean bean) throws AppException;
    Toy getItem(Connection c, int id) throws AppException;
}
