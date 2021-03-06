package ua.nure.kopaniev.cart;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ua.nure.kopaniev.bean.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserCartImpl implements UserCart, Serializable {
    private Map<Item, Integer> items = new LinkedHashMap<>();

    @Override
    public void add(Item item) {
        int count = items.getOrDefault(item, 0);
        items.put(item, ++count);
    }

    @Override
    public long increase(long id) {
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            if (entry.getKey().getId() == id) {
                int count = 1 + entry.getValue();
                items.put(entry.getKey(), count);
                return count;
            }
        }
        return 0;
    }

    @Override
    public long decrease(long id) {
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
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
    public void remove(long id) {
        Item t = null;
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
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
    public List<Item> getItems() {
        return new ArrayList<>(items.keySet());
    }

    @Override
    public int getCountOfItems(Item item) {
        return items.getOrDefault(item, 0);
    }

    @Override
    public long getCount() {
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
