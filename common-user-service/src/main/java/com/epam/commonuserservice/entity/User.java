package com.epam.commonuserservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "User")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Pattern(regexp = "^[A-Z][a-z]{1,18}$", message = "validator.wrong.user.name.input")
    private String userFirstName;
    @Pattern(regexp = "^[A-Z][a-z]{1,18}$", message = "validator.wrong.user.name.input")
    private String userLastName;
    @Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}", message = "validator.wrong.user.email.input")
    private String userEmail;
    private String userType;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+!=])(?=\\S+$).{4,}$", message = "validator.wrong.user.password.input")
    private String password;

    public User(long id, @Pattern(regexp = "^[A-Z][a-z]{1,18}$", message = "validator.wrong.user.name.input") String userFirstName, @Pattern(regexp = "^[A-Z][a-z]{1,18}$", message = "validator.wrong.user.name.input") String userLastName, @Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}", message = "validator.wrong.user.email.input") String userEmail, String userType) {
        this.id = id;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userType = userType;
    }
}
