package com.epam.commonuserservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Pattern(regexp = "^[A-Z][a-z]{1,18}$", message = "validator.wrong.user.name.input")
    private String firstName;
    @Pattern(regexp = "^[A-Z][a-z]{1,18}$", message = "validator.wrong.user.name.input")
    private String lastName;
    @Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}", message = "validator.wrong.user.email.input")
    private String email;
    private String userType;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+!=])(?=\\S+$).{4,}$", message = "validator.wrong.user.password.input")
    private String password;

    public User(long id, @Pattern(regexp = "^[A-Z][a-z]{1,18}$", message = "validator.wrong.user.name.input") String firstName, @Pattern(regexp = "^[A-Z][a-z]{1,18}$", message = "validator.wrong.user.name.input") String lastName, @Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}", message = "validator.wrong.user.email.input") String email, String userType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = userType;
    }
}
