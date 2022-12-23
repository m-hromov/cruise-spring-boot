package com.hromov.cruise.controller;

import com.hromov.cruise.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/sign_in")
@RequiredArgsConstructor
public class SignInController {
    private final UserService userService;

    @GetMapping
    public ModelAndView loadPage() {
        return new ModelAndView("signIn");
    }


}
