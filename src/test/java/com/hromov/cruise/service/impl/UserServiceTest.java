package com.hromov.cruise.service.impl;

import com.hromov.cruise.model.User;
import com.hromov.cruise.repository.UserRepository;
import com.hromov.cruise.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository, passwordEncoder);
    }

    @Test
    void testSavesUserAndEncodesItsPassword() {
        User user = User.builder()
                .accountNonExpired(true)
                .accountNonLocked(true)
                .authorities(Collections.emptySet())
                .username("test")
                .password("passwordTest")
                .build();
        User expected = User.builder()
                .id(1L)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .authorities(Collections.emptySet())
                .username("test")
                .password("encodedPassword")
                .build();
        given(userRepository.save(user)).willReturn(expected);
        given(passwordEncoder.encode(user.getPassword())).willReturn(expected.getPassword());

        User actual = userService.signUp(user);
        verify(userRepository, times(1)).save(user);
        verify(passwordEncoder, times(1)).encode("passwordTest");
        assertNotNull(actual);
        assertEquals(expected, actual);


    }
}