package com.tp.birthdayapp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "birthday")
public class Birthday {

    @Id
    private Long id;
    private LocalDate date;
    private String firstname;
    private String lastname;

    public Birthday() {
    }

    public Birthday(Long id, LocalDate date, String firstname, String lastname) {
        this.id = id;
        this.date = date;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}