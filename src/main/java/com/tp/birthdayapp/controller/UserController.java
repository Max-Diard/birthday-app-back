package com.tp.birthdayapp.controller;

import com.tp.birthdayapp.model.AppUser;
import com.tp.birthdayapp.service.UserServiceImpl;
import com.tp.birthdayapp.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("")
    ResponseEntity<List<AppUser>> findAllUsers() {
        return ResponseEntity.ok(userServiceImpl.findAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<AppUser> findUserById(@PathVariable String id) {
        try {
            AppUser appUser = userServiceImpl.findByAppUserId(Long.parseLong(id));
            return ResponseEntity.ok(appUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(Utils.setErrorHeadersRequestResponse(e)).build();
        }
    }

    @GetMapping("/mail/{email}")
    ResponseEntity<AppUser> findUserByEmail(@PathVariable String email) {
        try {
            AppUser appUser = userServiceImpl.findByEmail(email);
            return ResponseEntity.ok(appUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(Utils.setErrorHeadersRequestResponse(e)).build();
        }
    }

    @PostMapping("")
    ResponseEntity<AppUser> createUser(@RequestBody AppUser user) {
        try {
            AppUser appUser = userServiceImpl.create(user);
            return ResponseEntity.ok(appUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(Utils.setErrorHeadersRequestResponse(e)).build();
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<AppUser> updateUser(@PathVariable Long id, @RequestBody AppUser user) {
        try {
            AppUser appUser = userServiceImpl.update(this.userServiceImpl.getAppUser(id), user);
            return ResponseEntity.ok(appUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(Utils.setErrorHeadersRequestResponse(e)).build();
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            String response = userServiceImpl.delete(this.userServiceImpl.getAppUser(id));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(Utils.setErrorHeadersRequestResponse(e)).build();
        }
    }
}
