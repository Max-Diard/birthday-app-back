package com.tp.birthdayapp.service;

import com.tp.birthdayapp.model.Birthday;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface BasicService<S> {

    List<S> findAll();
    ResponseEntity<String> create(S entity);
    ResponseEntity<String> update(S entityToUpdate, S entity);
    ResponseEntity<String> delete(S entity);
}
