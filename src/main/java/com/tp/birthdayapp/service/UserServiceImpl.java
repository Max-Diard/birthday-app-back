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
public class UserServiceImpl implements BasicService<AppUser> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    public Optional<AppUser> findByAppUserId(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public ResponseEntity<String> create(AppUser user) {
        if (!userRepository.existsByEmail(user.getEmail())) {
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User has been created !");
        }
        System.out.println("User already exists !");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User already exists !");
    }

    @Override
    public ResponseEntity<String> update(Long id, AppUser user) {
        Optional<AppUser> optionalAppUser = userRepository.findById(id);
        if(optionalAppUser.isPresent()){
            optionalAppUser.get().setUsername(user.getUsername());
            optionalAppUser.get().setPassword(user.getPassword());
            optionalAppUser.get().setEmail(user.getEmail());
            userRepository.save(optionalAppUser.get());
            return ResponseEntity.status(HttpStatus.OK).body("User has been updated !");
        }
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body("User has been updated !");
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        Optional<AppUser> optionalAppUser = userRepository.findById(id);
        if(optionalAppUser.isPresent()){
            userRepository.delete(optionalAppUser.get());
            return ResponseEntity.status(HttpStatus.OK).body("User has been deleted !");
        } else {
            throw new NullPointerException("No user in databases");
        }
    }

    public Optional<AppUser> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
