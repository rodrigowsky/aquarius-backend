package com.rodrigowsky.aquarius.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Table(name = "student", schema = "public")
@Data
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "date_birth")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private Integer phoneNumber;

    //Assign User
    @OneToOne
    @MapsId
    @JoinColumn(name = "user", nullable = false)
    private User user;
}
