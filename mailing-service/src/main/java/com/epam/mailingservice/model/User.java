package com.epam.mailingservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The entity of user table.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String userType;
}
