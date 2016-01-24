package com.epam.preprod.kopaniev.repository;

import com.epam.preprod.kopaniev.AppException;
import com.epam.preprod.kopaniev.bean.Role;
import com.epam.preprod.kopaniev.bean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Vladyslav_Kopaniev on 11/11/2015.
 */
public class SQLUserRepository implements UserRepository {


    private static final String INSERT_USER = "INSERT INTO users(UserEmail, UserPassword, UserFirstName, UserLastName) VALUES (?, ?, ?, ?)";
    private static final String SELECT_USER = "SELECT * FROM users WHERE UserEmail = ?";

    @Override
    public void addUser(User u, Connection c) throws AppException {
        try {
            PreparedStatement stm = c.prepareStatement(INSERT_USER);
            stm.setString(1, u.getEmail());
            stm.setString(2, u.getPass());
            stm.setString(3, u.getName());
            stm.setString(4, u.getSurname());
            stm.executeUpdate();
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
    }

    @Override
    public User getUser(String email,  Connection c) throws AppException {
        try {
            PreparedStatement stm = c.prepareStatement(SELECT_USER);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            User user = null;

            if (rs != null && rs.next()) {
                String name = rs.getString("UserFirstName");
                String surename = rs.getString("UserLastName");
                String pass = rs.getString("UserPassword");
                int roleId = rs.getInt("UserRoleID");
                Role role = Role.values()[roleId - 1];
                user = new User(name, surename, email, pass, role, false, false);
            }

            return user;
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
    }
}
