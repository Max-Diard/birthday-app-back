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

    @GetMapping( "")
    List<AppUser> findAllUsers() {
        return userServiceImpl.findAll();
    };

    @GetMapping("/{id}")
    Optional<AppUser> findUserById(@PathVariable Long id) {
        return userServiceImpl.findByAppUserId(id);
    }

    @GetMapping("/{email}")
    Optional<AppUser> findUserById(@PathVariable String email) {
        return userServiceImpl.findByEmail(email);
    }

    @PostMapping("")
    ResponseEntity<String> createUser(@RequestBody AppUser user) {
        return userServiceImpl.create(user);
    }

    @PutMapping("/{id}")
    ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody AppUser user) {
        return userServiceImpl.update(id, user);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteUser(@PathVariable Long id) {
         return userServiceImpl.delete(id);
    }
}
