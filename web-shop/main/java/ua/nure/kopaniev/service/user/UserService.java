package ua.nure.kopaniev.service.user;

import ua.nure.kopaniev.util.AppException;
import ua.nure.kopaniev.bean.User;

/**
 * User details service.
 */
public interface UserService {
    void addUser(User u) throws AppException;

    User getUser(String email) throws AppException;

    User getCurrentUser();
}
