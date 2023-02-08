package com.rodrigowsky.aquarius.dto;

import com.rodrigowsky.aquarius.model.UserRoles;
import lombok.Data;
import java.sql.Date;

@Data
public class RegisterDTO {

    private String username;
    private String password;
    private UserRoles userRole;

    private String firstName;

    private String lastName;

    private Date dateOfBirth; //to calculate age
    private Integer phoneNumber;

}
