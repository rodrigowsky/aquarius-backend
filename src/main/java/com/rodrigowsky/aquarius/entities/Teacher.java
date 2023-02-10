package com.rodrigowsky.aquarius.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Table(name="teacher", schema = "public")
@Data
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="firstname")
    private String firstName;
    @Column(name="lastname")
    private String lastName;
    @Column(name="date_birth")
    private Date dateOfBirth;

    @Column(name="email")
    private String email;

    @Column(name="phone")
    private String phoneNumber;

    //Assign User
    @OneToOne
    @JoinColumn(name="user_fk", nullable=false)
    private User user;



}
