package ua.nure.kopaniev.repository;

import ua.nure.kopaniev.util.AppException;
import ua.nure.kopaniev.bean.User;

/**
 * User detail repository.
 */
public interface UserRepository {
    void addUser(User u) throws AppException;
    User getUser(String email)  throws AppException;
}
