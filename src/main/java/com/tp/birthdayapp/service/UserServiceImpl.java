package com.tp.birthdayapp.service;

import com.tp.birthdayapp.model.AppUser;
import com.tp.birthdayapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<AppUser> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<AppUser> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public ResponseEntity<String> createUser(AppUser user) {
        if (!userRepository.existsByEmail(user.getEmail())) {
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User has been created !");
        }
        System.out.println("User already exists !");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User already exists !");
    }

    @Override
    public void updateUser(AppUser user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(AppUser user) {
        userRepository.delete(user);
    }
}
