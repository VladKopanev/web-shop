package ua.nure.kopaniev.service.user;

import ua.nure.kopaniev.AppException;
import ua.nure.kopaniev.bean.User;

/**
 * Created by Vladyslav_Kopaniev on 11/10/2015.
 */
public interface UserService {
    void addUser(User u) throws AppException;

    User getUser(String email) throws AppException;
}
