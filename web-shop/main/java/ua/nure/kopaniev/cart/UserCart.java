package ua.nure.kopaniev.cart;

import ua.nure.kopaniev.bean.Item;

import java.util.List;

/**
 * User cart service.
 */
public interface UserCart {
    void add(Item item);

    long increase(long id);

    long decrease(long id);

    void remove(long id);

    void clear();

    List<Item> getItems();

    long getCountOfItems(Item item);

    long getCount();

    Float getSumOfItems();
}
