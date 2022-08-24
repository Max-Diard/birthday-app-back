package com.tp.birthdayapp.service;

import com.tp.birthdayapp.model.AppUser;
import com.tp.birthdayapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public AppUser findByAppUserId(Long id) throws Exception {
        return this.getAppUser(id);
    }

    @Override
    public AppUser create(AppUser user) throws Exception {
        if (user.getUsername() != null && user.getPassword() != null && user.getEmail() != null) {
            if (!userRepository.existsByEmail(user.getEmail())) {
                if (!userRepository.existsByUsername(user.getUsername())) {
                    userRepository.save(user);
                    return user;
                }
            }
        }
        throw new Exception("User can't be created !");
    }

    @Override
    public AppUser update(AppUser appUserToUpdate, AppUser user) {
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
        return appUserToUpdate;
    }

    @Override
    public String delete(AppUser appUser) {
        userRepository.delete(appUser);
        return "User has been deleted !";
    }

    public AppUser findByEmail(String email) throws Exception {
        Optional<AppUser> optionalAppUser = userRepository.findByEmail(email);
        if (optionalAppUser.isPresent()) {
            return optionalAppUser.get();
        }
        throw new Exception("User not found.");
    }

    public Optional<AppUser> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public AppUser getAppUser(Long userId) throws Exception {
        Optional<AppUser> optionalAppUser = this.userRepository.findById(userId);
        if (optionalAppUser.isPresent()) {
            return optionalAppUser.get();
        }
        throw new Exception("User not found.");
    }
}
