package ua.nure.kopaniev.service.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.kopaniev.bean.Item;
import ua.nure.kopaniev.bean.QueryBean;
import ua.nure.kopaniev.repository.ItemRepository;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository repository;

    @Override
    public List<Item> getItems(QueryBean bean) {
        return repository.getItems(bean);
    }

    @Override
    public int getCountOfItems(QueryBean bean) {
        return repository.getCountOfItems(bean);
    }

    @Override
    public Item getItem(long id) {
        return repository.getItem(id);
    }
}
