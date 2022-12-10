package com.hromov.cruise.service;

import javax.naming.AuthenticationException;

public interface UserService {
    void signIn(String email, String password) throws AuthenticationException;
}
