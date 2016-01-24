package com.epam.preprod.kopaniev.service.user;

import com.epam.preprod.kopaniev.AppException;
import com.epam.preprod.kopaniev.bean.User;
import com.epam.preprod.kopaniev.repository.UserRepository;
import com.epam.preprod.kopaniev.repository.transaction.TransactionException;
import com.epam.preprod.kopaniev.repository.transaction.TransactionManager;
import com.epam.preprod.kopaniev.repository.transaction.TransactionManagerImpl;

import javax.servlet.ServletContext;
import java.util.Optional;

/**
 * Created by Vladyslav_Kopaniev on 11/10/2015.
 */
public class UserServiceImpl implements UserService {
    private UserRepository repository;
    private TransactionManager manager;

    public UserServiceImpl(UserRepository repository, ServletContext context) {
        this.repository = repository;
         manager = (TransactionManagerImpl) (context.getAttribute("transactionManager"));
    }

    @Override
    public void addUser(User u) throws AppException {
        final UserRepository repo = repository;
        manager.executeQuery(c -> {
            repo.addUser(u, c);
            return Optional.empty();
        });
    }

    @Override
    public User getUser(String email) throws AppException {
        final UserRepository repo = repository;
        return  manager.executeQuery(c -> repo.getUser(email, c));
    }
}
