package ua.nure.kopaniev.cart;

import ua.nure.kopaniev.bean.Toy;

import java.util.List;

/**
 * Created by Vladyslav_Kopaniev on 11/25/2015.
 */
public interface UserCart {
    void add(Toy item);
    int increase(int id);
    int decrease(int id);
    void remove(int id);
    void clear();
    List<Toy> getItems();
    int getCountOfItems(Toy item);
    int getCount();
    Float getSumOfItems();
}
