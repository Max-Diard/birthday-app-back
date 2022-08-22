package com.tp.birthdayapp.service;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface BasicService<S> {

    List<S> findAll();
    Optional<S> findById(Long id);
    ResponseEntity<String> create(S entity);
    ResponseEntity<String> update(S entity);
    ResponseEntity<String> delete(S entity);
}
