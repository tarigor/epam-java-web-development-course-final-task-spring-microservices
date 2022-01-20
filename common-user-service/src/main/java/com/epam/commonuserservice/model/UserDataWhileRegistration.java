package com.epam.commonuserservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDataWhileRegistration {
    private String firstName;
    private String lastName;
    private String userType;
    private String email;
    private String password;
}
