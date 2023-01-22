package com.testtask.humaninfo.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;

    public User(String firstName, String lastName, LocalDate birthdate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
    }
}
