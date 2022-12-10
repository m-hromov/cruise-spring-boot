package com.hromov.cruise.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SignInController {

    @RequestMapping(value = "/sign_in", method = RequestMethod.GET)
    public ModelAndView loadPage() {
        return new ModelAndView("signIn");
    }
}
