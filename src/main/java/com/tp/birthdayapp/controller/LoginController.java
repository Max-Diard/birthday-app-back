package com.tp.birthdayapp.controller;

import com.tp.birthdayapp.model.Credential;
import com.tp.birthdayapp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("")
    ResponseEntity<String> authenticateUser(@RequestBody Credential credentials) {
        String username = credentials.getUsername();
        String password = credentials.getPassword();
        return userServiceImpl.login(username, password);
    }
}
