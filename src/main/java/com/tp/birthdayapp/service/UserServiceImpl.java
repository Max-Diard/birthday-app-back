package com.tp.birthdayapp.service;

import com.tp.birthdayapp.model.AppUser;
import com.tp.birthdayapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements BasicService<AppUser> {

    @Autowired
    private UserRepository userRepository;

    public AppUser login(String username, String password) throws Exception {
        Optional<AppUser> appUser = this.findByUsername(username);
        if (appUser.isPresent()) {
            if (password.equals(appUser.get().getPassword())) {
                return appUser.get();
            }
        }
        throw new Exception("Invalid Credentials, sorry dude !");
    }

    @Override
    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    public Optional<AppUser> findByAppUserId(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public ResponseEntity<String> create(AppUser user) {

        if (user.getUsername() != null && user.getPassword() != null && user.getEmail() != null) {
            if (!userRepository.existsByEmail(user.getEmail())) {
                if (!userRepository.existsByUsername(user.getUsername())) {
                    userRepository.save(user);
                    return ResponseEntity.status(HttpStatus.CREATED).body("User has been created !");
                } else {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("This username already taken !");
                }
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("This email already exists !");
            }
        } else {
            throw new NullPointerException("Can't be null");
        }
    }

    @Override
    public ResponseEntity<String> update(AppUser appUserToUpdate, AppUser user) {
        if (user.getUsername() != null) {
            appUserToUpdate.setUsername(user.getUsername());
        }
        if (user.getPassword() != null) {
            appUserToUpdate.setPassword(user.getPassword());
        }
        if (user.getEmail() != null) {
            appUserToUpdate.setEmail(user.getEmail());
        }
        userRepository.save(appUserToUpdate);
        return ResponseEntity.status(HttpStatus.OK).body("User has been updated !");
    }

    @Override
    public ResponseEntity<String> delete(AppUser appUser) {
        userRepository.delete(appUser);
        return ResponseEntity.status(HttpStatus.OK).body("User has been deleted !");
    }

    public Optional<AppUser> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<AppUser> findByUsername(String username) {
        return  userRepository.findByUsername(username);
    }

    public AppUser getAppUser(Long userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found."));
    }
}
