package com.tp.birthdayapp.controller;

import com.tp.birthdayapp.model.Birthday;
import com.tp.birthdayapp.service.BirthdayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users/{userId}/birthdays")
public class BirthdayController {

    @Autowired
    private BirthdayServiceImpl birthdayServiceImpl;

    @GetMapping("")
    public List<Birthday> findAllBirthdaysByAppUserId(@PathVariable Long userId) {
        return birthdayServiceImpl.findAllBirthdaysByAppUserId(userId);
    }

    @GetMapping("/{birthdayId}")
    public Optional<Birthday> findBirthdayByUserIdAndBirthdayId(@PathVariable Long userId, @PathVariable Long birthdayId) {
        return birthdayServiceImpl.findBirthdayByUserIdAndBirthdayId(userId, birthdayId);
    }

    @PostMapping("")
    public ResponseEntity<String> createBirthday(@PathVariable Long userId, @RequestBody Birthday birthday) {
        try {
            return birthdayServiceImpl.createBirthdayWithAppUser(userId, birthday);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("All Fields must be completed.");
        }
    }

    @PutMapping("/{birthdayId}")
    ResponseEntity<String> updateBirthday(@PathVariable Long userId, @PathVariable Long birthdayId, @RequestBody Birthday birthday) {
        return birthdayServiceImpl.updateWithUserIdAndBirthdayId(userId, birthdayId, birthday);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteBirthday(@PathVariable Long userId, @PathVariable Long id) {
        return birthdayServiceImpl.deleteWithUserIdAndBirthdayId(userId, id);
    }
}
