package com.tp.birthdayapp.controller;

import com.tp.birthdayapp.model.Birthday;
import com.tp.birthdayapp.service.BirthdayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/birthdays")
public class BirthdayController {

    @Autowired
    private BirthdayServiceImpl birthdayServiceImpl;

    @GetMapping("/")
    public List<Birthday> findAllBirthdays() {
        //Todo: A changer avec le bon utilisateur
        return birthdayServiceImpl.findAll();
    }

    @GetMapping("/users/{id}")
    public List<Birthday> findAllBirthdaysByAppUserId(@PathVariable Long id) {
        return birthdayServiceImpl.findAllBirthdaysByAppUserId(id);
    }

    @GetMapping("/{id}")
    public Optional<Birthday> findBirthdayById(@PathVariable Long id) {
        return birthdayServiceImpl.findById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<String> createBirthday(@RequestBody Birthday birthday) {
        return birthdayServiceImpl.create(birthday);
    }

    @PutMapping("/update")
    ResponseEntity<String> updateBirthday(@RequestBody Birthday birthday) {
        return birthdayServiceImpl.update(birthday);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteBirthday(@PathVariable Long id) {
        return birthdayServiceImpl.delete(id);
    }
}
