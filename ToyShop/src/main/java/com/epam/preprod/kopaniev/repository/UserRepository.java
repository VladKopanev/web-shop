package com.epam.preprod.kopaniev.repository;

import com.epam.preprod.kopaniev.AppException;
import com.epam.preprod.kopaniev.bean.User;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Vladyslav_Kopaniev on 11/10/2015.
 */
public interface UserRepository {
    void addUser(User u,  Connection c) throws AppException;
    User getUser(String email,  Connection c)  throws AppException;
}
