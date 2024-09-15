package ru.redrik.spring_securityApp.controllers;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.redrik.spring_securityApp.security.UserDetailsImpl;

@Controller
public class userController {

    @GetMapping("/")
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/userInfo")
    public String showUser() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        UserDetailsImpl userDetails =  (UserDetailsImpl) authentication;
        System.out.println(userDetails.getUser());

        return "hello";
    }
}
