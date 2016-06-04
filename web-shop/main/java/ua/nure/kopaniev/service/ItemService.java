package ua.nure.kopaniev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.kopaniev.bean.Item;
import ua.nure.kopaniev.bean.QueryBean;
import ua.nure.kopaniev.repository.ItemRepository;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    public List<Item> getItems(QueryBean bean) {
        return repository.getItems(bean);
    }

    public int getCountOfItems(QueryBean bean) {
        return repository.getCountOfItems(bean);
    }

    public Item getItem(long id) {
        return repository.getItem(id);
    }
}
