package com.epam.preprod.kopaniev.repository;

import com.epam.preprod.kopaniev.bean.User;

import java.sql.Connection;
import java.util.HashMap;

/**
 * Created by Vladyslav_Kopaniev on 11/10/2015.
 */
public class UserRepositoryImpl implements UserRepository {
    private static UserRepositoryImpl ourInstance = new UserRepositoryImpl();
    HashMap<String, User> users = new HashMap<>();

    public static UserRepositoryImpl getInstance() {
        return ourInstance;
    }

    @Override
    public void addUser(User u,  Connection c) {
        users.put(u.getEmail(), u);
    }

    @Override
    public User getUser(String email, Connection c) {
        return users.get(email);
    }

    private UserRepositoryImpl() {
    }
}
