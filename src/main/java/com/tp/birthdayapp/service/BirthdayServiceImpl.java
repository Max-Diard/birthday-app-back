package com.tp.birthdayapp.service;

import com.tp.birthdayapp.model.AppUser;
import com.tp.birthdayapp.model.Birthday;
import com.tp.birthdayapp.repository.BirthdayRepository;
import com.tp.birthdayapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BirthdayServiceImpl implements BasicService<Birthday> {

    @Autowired
    private BirthdayRepository birthdayRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Birthday> findAll() {
        return birthdayRepository.findAll();
    }

    @Override
    public Optional<Birthday> findById(Long id) {
        return birthdayRepository.findById(id);
    }

    @Override
    public ResponseEntity<String> create(Birthday birthday) {
        birthdayRepository.save(birthday);
        return ResponseEntity.status(HttpStatus.CREATED).body("Birthday has been created !");
    }

    @Override
    public ResponseEntity<String> update(Birthday birthday) {
        birthdayRepository.save(birthday);
        return ResponseEntity.status(HttpStatus.OK).body("Birthday has been updated !");
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        Optional<Birthday> optionalBirthday = birthdayRepository.findById(id);
        if(optionalBirthday.isPresent()){
            birthdayRepository.delete(optionalBirthday.get());
            return ResponseEntity.status(HttpStatus.OK).body("Birthday has been deleted !");
        } else {
            throw new NullPointerException("No user in databases");
        }
    }

    public List<Birthday> findAllBirthdaysByAppUserId(Long id){
        Optional<AppUser> optionalAppUser = userRepository.findById(id);
        if(optionalAppUser.isPresent()){
            return birthdayRepository.findBirthdayByAppUser(optionalAppUser.get());
        } else {
            throw new NullPointerException("No user in databases");
        }
    }
}
