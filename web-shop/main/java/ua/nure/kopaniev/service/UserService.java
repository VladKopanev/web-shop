package ua.nure.kopaniev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ua.nure.kopaniev.bean.User;
import ua.nure.kopaniev.repository.UserRepository;

@Service("userService")
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    public void addUser(User user) {
        repository.addUser(user);
    }

    public User getUser(String email) {
        return loadUserByUsername(email);
    }

    public User getCurrentUser() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    @Override
    public User loadUserByUsername(String email) {
        return repository.getUser(email);
    }
}
