package com.tp.birthdayapp.model;

import lombok.Data;

@Data
public class Credential {

    private String username;
    private String password;

    public Credential(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
