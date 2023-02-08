package com.rodrigowsky.aquarius.entities;

import com.rodrigowsky.aquarius.model.UserRoles;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

//OWning side
@Entity
@Table(name="authority", schema = "public")
@Data
@NoArgsConstructor
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private UserRoles name;

    @ManyToOne
    @JoinColumn(name = "user.id",nullable=false)
    private User username;

}
