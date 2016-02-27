package ua.nure.kopaniev.repository;

import ua.nure.kopaniev.AppException;
import ua.nure.kopaniev.bean.User;

import java.sql.Connection;

/**
 * Created by Vladyslav_Kopaniev on 11/10/2015.
 */
public interface UserRepository {
    void addUser(User u, Connection c) throws AppException;
    User getUser(String email,  Connection c)  throws AppException;
}
