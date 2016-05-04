package ua.nure.kopaniev.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ua.nure.kopaniev.bean.User;
import ua.nure.kopaniev.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public void addUser(User user) {
        repository.addUser(user);
    }

    @Override
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
