package com.epam.preprod.kopaniev.service.user;

import com.epam.preprod.kopaniev.AppException;
import com.epam.preprod.kopaniev.bean.User;

/**
 * Created by Vladyslav_Kopaniev on 11/10/2015.
 */
public interface UserService {
    void addUser(User u) throws AppException;

    User getUser(String email) throws AppException;
}
