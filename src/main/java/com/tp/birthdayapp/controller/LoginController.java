package com.tp.birthdayapp.controller;

import com.tp.birthdayapp.model.AppUser;
import com.tp.birthdayapp.model.Credential;
import com.tp.birthdayapp.service.UserServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
    ResponseEntity<AppUser> authenticateUser(@RequestBody Credential credentials) {
        String username = credentials.getUsername();
        String password = credentials.getPassword();
        try {
            AppUser appUser = userServiceImpl.login(username, password);
            // Retourne les données du appUser dans le body de la reponse.
            return ResponseEntity.ok(appUser);
        } catch (Exception e) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("errorMessage", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(responseHeaders).build();
        }
    }
}
