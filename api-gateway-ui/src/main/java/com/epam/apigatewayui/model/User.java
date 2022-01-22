package com.epam.apigatewayui.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * The entity of user table.
 */
@Data
public class User {
    @JsonProperty("id")
    private long id;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("userType")
    private String userType;
}
