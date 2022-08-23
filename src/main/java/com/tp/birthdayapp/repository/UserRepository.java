package com.tp.birthdayapp.repository;

import com.tp.birthdayapp.model.AppUser;
import com.tp.birthdayapp.model.Birthday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
    boolean existsByEmail(String email);
}
