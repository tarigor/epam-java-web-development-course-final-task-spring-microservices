package com.epam.apigatewayui.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDataWhileRegistration {
    @Pattern(regexp = "^[A-Z]+([._]?[a-zA-Z]+)*$")
    private String firstName;
    @Pattern(regexp = "^[A-Z]+([._]?[a-zA-Z]+)*$")
    private String lastName;
    private String userType;
    @Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}")
    private String email;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+!=])(?=\\S+$).{4,}$")
    private String password;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+!=])(?=\\S+$).{4,}$")
    private String repeatedPassword;
}
