package com.tp.birthdayapp.controller;

import com.tp.birthdayapp.model.AppUser;
import com.tp.birthdayapp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("")
    List<AppUser> findAllUsers() {
        return userServiceImpl.findAll();
    }

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
        try {
            return userServiceImpl.create(user);
        } catch (NullPointerException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("All Fields must be completed.");
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody AppUser user) {
        return userServiceImpl.update(this.userServiceImpl.getAppUser(id), user);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return userServiceImpl.delete(this.userServiceImpl.getAppUser(id));
    }
}
