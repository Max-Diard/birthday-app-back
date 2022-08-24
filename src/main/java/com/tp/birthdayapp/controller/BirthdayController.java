package com.tp.birthdayapp.controller;

import com.tp.birthdayapp.model.Birthday;
import com.tp.birthdayapp.service.BirthdayServiceImpl;
import com.tp.birthdayapp.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/birthdays")
public class BirthdayController {

    @Autowired
    private BirthdayServiceImpl birthdayServiceImpl;

    @GetMapping("")
    public ResponseEntity<List<Birthday>> findAllBirthdaysByAppUserId(@PathVariable Long userId) {
        try {
            List<Birthday> birthdayList = birthdayServiceImpl.findAllBirthdaysByAppUserId(userId);
            return ResponseEntity.ok(birthdayList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(Utils.setErrorHeadersRequestResponse(e)).build();
        }
    }

    @GetMapping("/{birthdayId}")
    public ResponseEntity<Birthday> findBirthdayByUserIdAndBirthdayId(@PathVariable Long userId, @PathVariable Long birthdayId) {
        try {
            Birthday birthday = birthdayServiceImpl.findBirthdayByUserIdAndBirthdayId(userId, birthdayId);
            return ResponseEntity.ok(birthday);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(Utils.setErrorHeadersRequestResponse(e)).build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Birthday> createBirthday(@PathVariable Long userId, @RequestBody Birthday birthday) {
        try {
            Birthday birthdayCreated = birthdayServiceImpl.createBirthdayWithAppUser(userId, birthday);
            return ResponseEntity.ok(birthdayCreated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(Utils.setErrorHeadersRequestResponse(e)).build();
        }
    }

    @PutMapping("/{birthdayId}")
    ResponseEntity<Birthday> updateBirthday(@PathVariable Long userId, @PathVariable Long birthdayId, @RequestBody Birthday birthday) {
        try {
            Birthday birthdayUpdated = birthdayServiceImpl.updateWithUserIdAndBirthdayId(userId, birthdayId, birthday);
            return ResponseEntity.ok(birthdayUpdated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(Utils.setErrorHeadersRequestResponse(e)).build();
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteBirthday(@PathVariable Long userId, @PathVariable Long id) {
        try {
            String response = birthdayServiceImpl.deleteWithUserIdAndBirthdayId(userId, id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(Utils.setErrorHeadersRequestResponse(e)).build();
        }
    }
}
