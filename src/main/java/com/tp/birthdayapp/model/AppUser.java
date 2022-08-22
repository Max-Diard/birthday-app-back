package com.tp.birthdayapp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user", schema = "public")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;

    public AppUser(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public AppUser() {
    }
}
