package com.tp.birthdayapp.service;

import com.tp.birthdayapp.model.Birthday;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface BasicService<S> {

    List<S> findAll();
    S create(S entity) throws Exception;
    S update(S entityToUpdate, S entity) throws Exception;
    String delete(S entity) throws Exception;
}
