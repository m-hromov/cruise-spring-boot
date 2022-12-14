package com.hromov.cruise.service.impl;

import com.hromov.cruise.model.User;
import com.hromov.cruise.repository.UserRepository;
import com.hromov.cruise.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signIn(String email, String password) throws AuthenticationException {
        Optional<User> optional = userRepository.findByEmail(email);
        User user = optional.orElseThrow(AuthenticationException::new);
        String userActualPassword = user.getPassword();
        if (!passwordEncoder.matches(password, userActualPassword)) {
            log.info("Attempt to sign in with " + email + "failed");
            throw new AuthenticationException("Invalid password!");
        }
    }

    @Override
    public User signUp(User user) {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }
}
