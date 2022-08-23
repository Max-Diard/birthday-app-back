package com.tp.birthdayapp.service;

import com.tp.birthdayapp.model.AppUser;
import com.tp.birthdayapp.model.Birthday;
import com.tp.birthdayapp.repository.BirthdayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class BirthdayServiceImpl implements BasicService<Birthday> {

    @Autowired
    private BirthdayRepository birthdayRepository;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public List<Birthday> findAll() {
        return birthdayRepository.findAll();
    }

    public Optional<Birthday> findBirthdayByUserIdAndBirthdayId(Long userId, Long birthdayId) {
        AppUser appUser = this.userService.getAppUser(userId);
        return birthdayRepository.findBirthdayByAppUser_IdAndId(appUser.getId(), birthdayId);
    }

    @Override
    public ResponseEntity<String> create(Birthday birthday) {
        birthdayRepository.save(birthday);
        return ResponseEntity.status(HttpStatus.CREATED).body("Birthday has been created !");
    }

    public ResponseEntity<String> updateWithUserIdAndBirthdayId(Long userId, Long birthdayId, Birthday birthday) {
        AppUser appUser = this.userService.getAppUser(userId);
        Birthday birthdayRepo = this.getBirthdayWithId(birthdayId);

        if (birthdayRepo.getAppUser().getId().equals(appUser.getId())) {
            return this.update(birthdayRepo, birthday);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You can't update this birthday.");
        }
    }

    @Override
    public ResponseEntity<String> update(Birthday birthdayToUpdate, Birthday birthday) {
        if (birthday.getFirstname() != null) {
            birthdayToUpdate.setFirstname(birthday.getFirstname());
        }
        if (birthday.getLastname() != null) {
            birthdayToUpdate.setLastname(birthday.getLastname());
        }
        if (birthday.getDate() != null) {
            birthdayToUpdate.setDate(birthday.getDate());
        }

        birthdayRepository.save(birthdayToUpdate);
        return ResponseEntity.status(HttpStatus.OK).body("Birthday has been updated !");
    }

    public ResponseEntity<String> deleteWithUserIdAndBirthdayId(Long userId, Long birthdayId) {
        AppUser appUser = this.userService.getAppUser(userId);
        Birthday birthdayRepo = this.getBirthdayWithId(birthdayId);

        if (birthdayRepo.getAppUser().getId().equals(appUser.getId())) {
            return this.delete(birthdayRepo);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You can't delete this birthday.");
        }
    }

    @Override
    public ResponseEntity<String> delete(Birthday birthday) {
        birthdayRepository.delete(birthday);
        return ResponseEntity.status(HttpStatus.OK).body("Birthday has been deleted !");
    }

    public List<Birthday> findAllBirthdaysByAppUserId(Long id) {
        AppUser appUser = this.userService.getAppUser(id);
        return birthdayRepository.findBirthdayByAppUser(appUser);
    }

    public ResponseEntity<String> createBirthdayWithAppUser(Long userId, Birthday birthday) {
        AppUser appUser = this.userService.getAppUser(userId);
        birthday.setAppUser(appUser);
        return this.create(birthday);
    }

    private Birthday getBirthdayWithId(Long birthdayId) {
        return this.birthdayRepository.findById(birthdayId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Birthday not found."));
    }
}
