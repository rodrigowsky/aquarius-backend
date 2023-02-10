package com.rodrigowsky.aquarius.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rodrigowsky.aquarius.model.UserRoles;
import lombok.Data;

import java.util.Date;

@Data
public class RegisterDTO {

    private String username;
    private String password;
    private UserRoles userRole;
    private String firstName;
    private String lastName;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth; //to calculate age
    private String phoneNumber;
    private String email;

}
