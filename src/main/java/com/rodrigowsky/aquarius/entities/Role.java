package com.rodrigowsky.aquarius.entities;

import com.rodrigowsky.aquarius.model.UserRoles;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles", schema = "public")
@Data
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "name")
    private UserRoles name;

//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE
//            },
//            mappedBy = "roles")
//    private Set<User> users = new HashSet<>();



//    public void setUser(Set<User> user){
//        this.users = user;
//    }

}
