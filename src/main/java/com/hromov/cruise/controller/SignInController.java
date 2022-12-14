package com.hromov.cruise.controller;

import com.hromov.cruise.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/sign_in")
@RequiredArgsConstructor
public class SignInController {
    private final UserService userService;

    @GetMapping(value = "/")
    public ModelAndView loadPage() {
        return new ModelAndView("signIn");
    }

    @PostMapping(value = "/")
    public ModelAndView signIn(String email, String password) {
        ModelAndView modelAndView = new ModelAndView("index");
        try {
            userService.signIn(email, password);
        } catch (AuthenticationException e) {
            modelAndView.addObject("authFailed", true);
        }
        return modelAndView;
    }
}
