package com.tp.birthdayapp.service;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface BasicService<S> {

    List<S> findAll();
    ResponseEntity<String> create(S entity);
    ResponseEntity<String> update(Long id, S entity);
    ResponseEntity<String> delete(Long id);
}
