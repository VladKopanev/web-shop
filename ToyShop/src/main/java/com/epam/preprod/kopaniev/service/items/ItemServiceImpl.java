package com.epam.preprod.kopaniev.service.items;

import com.epam.preprod.kopaniev.AppException;
import com.epam.preprod.kopaniev.bean.QueryBean;
import com.epam.preprod.kopaniev.bean.Toy;
import com.epam.preprod.kopaniev.repository.ItemRepository;
import com.epam.preprod.kopaniev.repository.UserRepository;
import com.epam.preprod.kopaniev.repository.transaction.TransactionManager;
import com.epam.preprod.kopaniev.repository.transaction.TransactionManagerImpl;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.util.List;

/**
 * Created by Vladyslav_Kopaniev on 11/18/2015.
 */
public class ItemServiceImpl implements ItemService {
    private ItemRepository repository;
    private TransactionManager manager;

    public ItemServiceImpl(ItemRepository repository, ServletContext context) {
        this.repository = repository;
        manager = (TransactionManagerImpl) (context.getAttribute("transactionManager"));
    }

    @Override
    public List<Toy> getItems(QueryBean bean) throws AppException {
        return manager.executeQuery(c -> repository.getItems(c, bean));
    }

    @Override
    public int getCountOfItems(QueryBean bean) throws AppException {
        return manager.executeQuery(c-> repository.getCountOfItems(c, bean));
    }

    @Override
    public Toy getItem(int id) throws AppException {
        return manager.executeQuery(c -> repository.getItem(c, id));
    }
}
