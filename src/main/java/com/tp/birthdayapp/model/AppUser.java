package com.tp.birthdayapp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user")
public class AppUser {

    @Id
    private Long id;
    private String username;
    private String password;
    private String email;

    public AppUser(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public AppUser() {
    }
}
