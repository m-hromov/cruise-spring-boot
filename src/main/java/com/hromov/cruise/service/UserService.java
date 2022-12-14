package com.hromov.cruise.service;

import com.hromov.cruise.model.User;

import javax.naming.AuthenticationException;

public interface UserService {
    void signIn(String email, String password) throws AuthenticationException;

    User signUp(User user);
}
