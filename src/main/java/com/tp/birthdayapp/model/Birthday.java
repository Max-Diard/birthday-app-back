package com.tp.birthdayapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "birthday")
public class Birthday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private AppUser appUser;

    private LocalDate date;
    private String firstname;
    private String lastname;

    public Birthday() {
    }

    public Birthday(AppUser appUser, LocalDate date, String firstname, String lastname) {
        this.appUser = appUser;
        this.date = date;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}