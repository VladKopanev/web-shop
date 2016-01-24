package com.epam.preprod.kopaniev.service.items;

import javax.servlet.ServletContext;

/**
 * Created by Vladyslav_Kopaniev on 11/18/2015.
 */
public interface ItemServiceFactory {
    ItemService getItemService(ServletContext context);
}
