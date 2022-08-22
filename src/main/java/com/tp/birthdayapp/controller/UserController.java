package com.tp.birthdayapp.controller;

import com.tp.birthdayapp.model.AppUser;
import com.tp.birthdayapp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/")
    List<AppUser> findAllUsers() {
        return userServiceImpl.findAll();
    };

    @GetMapping("/{id}")
    Optional<AppUser> findUserById(@RequestParam Long id) {
        return userServiceImpl.findById(id);
    }

    @GetMapping("/{email}")
    Optional<AppUser> findUserById(@RequestParam String email) {
        return userServiceImpl.findByEmail(email);
    }

    @PostMapping("/add")
    ResponseEntity<String> createUser(@RequestBody AppUser user) {
        return userServiceImpl.createUser(user);
    }

    @PutMapping("/update")
    void updateUser(@RequestBody AppUser user) {
        userServiceImpl.updateUser(user);
    }

    @DeleteMapping("/delete")
    void deleteUser(AppUser user) {
        userServiceImpl.deleteUser(user);
    }
}
