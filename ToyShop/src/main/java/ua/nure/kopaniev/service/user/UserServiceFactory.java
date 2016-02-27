package ua.nure.kopaniev.service.user;

import javax.servlet.ServletContext;

/**
 * Created by Vladyslav_Kopaniev on 11/10/2015.
 */
public interface UserServiceFactory {
    UserService getUserService(ServletContext context);
}
