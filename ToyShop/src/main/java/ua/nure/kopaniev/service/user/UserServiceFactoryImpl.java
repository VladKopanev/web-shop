package ua.nure.kopaniev.service.user;

import ua.nure.kopaniev.repository.SQLUserRepository;

import javax.servlet.ServletContext;

/**
 * Created by Vladyslav_Kopaniev on 11/10/2015.
 */
public class UserServiceFactoryImpl implements UserServiceFactory {
    @Override
    public UserService getUserService(ServletContext context) {
        return new UserServiceImpl(new SQLUserRepository(), context);
    }
}
