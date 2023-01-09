package com.hromov.cruise.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/sign_in")
@RequiredArgsConstructor
public class SignInController {
    @GetMapping
    public ModelAndView loadPage() {
        return new ModelAndView("signIn");
    }

    @GetMapping("/user")
    public Authentication user(Authentication principal) {
        return principal;
    }
}
