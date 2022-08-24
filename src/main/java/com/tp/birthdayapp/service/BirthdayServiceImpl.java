package com.tp.birthdayapp.service;

import com.tp.birthdayapp.model.AppUser;
import com.tp.birthdayapp.model.Birthday;
import com.tp.birthdayapp.repository.BirthdayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    public Birthday findBirthdayByUserIdAndBirthdayId(Long userId, Long birthdayId) throws Exception {
        AppUser appUser = this.userService.getAppUser(userId);
        Optional<Birthday> birthday = birthdayRepository.findBirthdayByAppUser_IdAndId(appUser.getId(), birthdayId);
        if (birthday.isPresent()) {
            return birthday.get();
        }
        throw new Exception("Birthday not found !");
    }

    @Override
    public Birthday create(Birthday birthday) {
        birthdayRepository.save(birthday);
        return birthday;
    }

    public Birthday updateWithUserIdAndBirthdayId(Long userId, Long birthdayId, Birthday birthday) throws Exception {
        AppUser appUser = this.userService.getAppUser(userId);
        Birthday birthdayRepo = this.getBirthdayWithId(birthdayId);

        if (birthdayRepo.getAppUser().getId().equals(appUser.getId())) {
            return this.update(birthdayRepo, birthday);
        }
        throw new Exception("You can't update this birthday.");

    }

    @Override
    public Birthday update(Birthday birthdayToUpdate, Birthday birthday) {
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
        return birthdayToUpdate;
    }

    public String deleteWithUserIdAndBirthdayId(Long userId, Long birthdayId) throws Exception {
        AppUser appUser = this.userService.getAppUser(userId);
        Birthday birthdayRepo = this.getBirthdayWithId(birthdayId);

        if (birthdayRepo.getAppUser().getId().equals(appUser.getId())) {
            return this.delete(birthdayRepo);
        }
        throw new Exception("You can't delete this birthday.");
    }

    @Override
    public String delete(Birthday birthday) {
        birthdayRepository.delete(birthday);
        return "Birthday has been deleted !";
    }

    public List<Birthday> findAllBirthdaysByAppUserId(Long id) throws Exception {
        AppUser appUser = this.userService.getAppUser(id);
        return birthdayRepository.findBirthdayByAppUser(appUser);
    }

    public Birthday createBirthdayWithAppUser(Long userId, Birthday birthday) throws Exception {
        if (birthday.getFirstname() != null && birthday.getLastname() != null && birthday.getDate() != null) {
            AppUser appUser = this.userService.getAppUser(userId);
            birthday.setAppUser(appUser);
            return this.create(birthday);
        } else {
            throw new NullPointerException("All fields must be completed !");
        }
    }

    private Birthday getBirthdayWithId(Long birthdayId) {
        return this.birthdayRepository.findById(birthdayId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Birthday not found."));
    }
}
