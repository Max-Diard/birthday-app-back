package com.tp.birthdayapp.repository;

import com.tp.birthdayapp.model.AppUser;
import com.tp.birthdayapp.model.Birthday;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BirthdayRepository extends JpaRepository<Birthday, Long> {
    List<Birthday> findBirthdayByAppUser(AppUser appUser);
    Optional<Birthday> findBirthdayByAppUser_IdAndId(Long userId, Long birthdayId);
}
