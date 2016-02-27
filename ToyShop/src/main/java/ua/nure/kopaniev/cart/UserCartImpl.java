package ua.nure.kopaniev.cart;

import ua.nure.kopaniev.bean.Toy;

import java.util.*;


public class UserCartImpl implements UserCart {
    Map<Toy, Integer> items = new LinkedHashMap<>();

    @Override
    public void add(Toy item) {
        int count = items.getOrDefault(item, 0);
        items.put(item, ++count);
    }

    @Override
    public int increase(int id) {
        for (Map.Entry<Toy, Integer> entry : items.entrySet()) {
            if (entry.getKey().getId() == id) {
                int count = 1 + entry.getValue();
                items.put(entry.getKey(), count);
                return count;
            }
        }
        return 0;
    }

    @Override
    public int decrease(int id) {
        for (Map.Entry<Toy, Integer> entry : items.entrySet()) {
            if (entry.getKey().getId() == id) {
                int count = entry.getValue() - 1;
                if (count >= 1) {
                    entry.setValue(count);
                }
                return count;
            }
        }
        return 0;
    }

    @Override
    public void remove(int id) {
        Toy t = null;
        for (Map.Entry<Toy, Integer> entry : items.entrySet()) {
            if (entry.getKey().getId() == id) {
                t = entry.getKey();
                break;
            }
        }
        items.remove(t);
    }

    @Override
    public void clear() {
        items.clear();
    }

    @Override
    public List<Toy> getItems() {
        return new ArrayList<>(items.keySet());
    }

    @Override
    public int getCountOfItems(Toy item) {
        return items.getOrDefault(item, 0);
    }

    @Override
    public int getCount() {
        return items
                .values()
                .stream()
                .reduce((c1, c2) -> c1 + c2)
                .orElse(0);
    }

    @Override
    public Float getSumOfItems() {
        return items
                .entrySet()
                .stream()
                .map(e -> e.getKey().getPrice() * e.getValue())
                .reduce((p1, p2) -> p1 + p2)
                .orElse(0f);
    }
}
