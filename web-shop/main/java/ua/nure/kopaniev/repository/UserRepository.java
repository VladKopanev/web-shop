package ua.nure.kopaniev.repository;

import ua.nure.kopaniev.bean.User;

/**
 * User detail repository.
 */
public interface UserRepository {
    void addUser(User u);

    User getUser(String email);
}
