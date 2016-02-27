package ua.nure.kopaniev.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.nure.kopaniev.util.AppException;
import ua.nure.kopaniev.bean.User;
import ua.nure.kopaniev.repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public void addUser(User user) throws AppException {
        repository.addUser(user);
    }

    @Override
    public User getUser(String email) throws AppException {
        return loadUserByUsername(email);
    }

    public User getCurrentUser() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.getUser(email);
    }
}
