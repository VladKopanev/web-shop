package ua.nure.kopaniev.service.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.kopaniev.bean.Item;
import ua.nure.kopaniev.bean.QueryBean;
import ua.nure.kopaniev.repository.ItemRepository;
import ua.nure.kopaniev.util.AppException;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository repository;

    @Override
    public List<Item> getItems(QueryBean bean) throws AppException {
        return repository.getItems(bean);
    }

    @Override
    public int getCountOfItems(QueryBean bean) throws AppException {
        return repository.getCountOfItems(bean);
    }

    @Override
    public Item getItem(long id) throws AppException {
        return repository.getItem(id);
    }
}
