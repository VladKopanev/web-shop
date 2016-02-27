package ua.nure.kopaniev.service.items;

import ua.nure.kopaniev.repository.SQLItemRepository;

import javax.servlet.ServletContext;

/**
 * Created by Vladyslav_Kopaniev on 11/18/2015.
 */
public class ItemServiceFactoryImpl implements ItemServiceFactory {
    @Override
    public ItemService getItemService(ServletContext context) {
        return new ItemServiceImpl(new SQLItemRepository(), context);
    }
}
