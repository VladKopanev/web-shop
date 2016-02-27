package ua.nure.kopaniev.service.items;

import ua.nure.kopaniev.AppException;
import ua.nure.kopaniev.bean.QueryBean;
import ua.nure.kopaniev.bean.Toy;
import ua.nure.kopaniev.repository.ItemRepository;
import ua.nure.kopaniev.repository.transaction.TransactionManager;
import ua.nure.kopaniev.repository.transaction.TransactionManagerImpl;

import javax.servlet.ServletContext;
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
