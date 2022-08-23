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

    public Optional<Birthday> findBirthdayByUserIdAndBirthdayId(Long userId, Long birthdayId) {
        Optional<AppUser> appUser = userRepository.findById(userId);
        if (appUser.isPresent()) {
            return birthdayRepository.findBirthdayByAppUser_IdAndId(appUser.get().getId(), birthdayId);
        }
        return null;
    }

    @Override
    public ResponseEntity<String> create(Birthday birthday) {
        birthdayRepository.save(birthday);
        return ResponseEntity.status(HttpStatus.CREATED).body("Birthday has been created !");
    }

    @Override
    public ResponseEntity<String> update(Long id, Birthday birthday) {
        Optional<Birthday> optionalBirthday = birthdayRepository.findById(id);
        if (optionalBirthday.isPresent()) {
            optionalBirthday.get().setFirstname(birthday.getFirstname());
            optionalBirthday.get().setLastname(birthday.getLastname());
            optionalBirthday.get().setDate(birthday.getDate());
            birthdayRepository.save(optionalBirthday.get());
            return ResponseEntity.status(HttpStatus.OK).body("User has been updated !");
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Birthday has not been updated !");
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
