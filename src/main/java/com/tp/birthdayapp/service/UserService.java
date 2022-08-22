package com.tp.birthdayapp.service;

import com.tp.birthdayapp.model.AppUser;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<AppUser> findAll();
    Optional<AppUser> findById(Long id);
    Optional<AppUser> findByEmail(String email);
    ResponseEntity<String> createUser(AppUser user);
    void updateUser(AppUser user);
    void deleteUser(AppUser user);
}
