package ua.nure.kopaniev.service.user;

import ua.nure.kopaniev.bean.User;

/**
 * User details service.
 */
public interface UserService {
    void addUser(User u);

    User getUser(String email);

    User getCurrentUser();
}
