package com.hromov.cruise.service.impl;

import com.hromov.cruise.model.User;
import com.hromov.cruise.repository.UserRepository;
import com.hromov.cruise.service.UserService;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void signIn(String email, String password) throws AuthenticationException {
        Optional<User> optional = userRepository.findByEmail(email);
        User user = optional.orElseThrow();
        String userActualPassword = user.getPassword();
        String salt = user.getPasswordSalt();
    }
}
