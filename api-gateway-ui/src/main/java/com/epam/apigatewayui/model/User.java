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
    @JsonProperty("userFirstName")
    private String userFirstName;
    @JsonProperty("userLastName")
    private String userLastName;
    @JsonProperty("userEmail")
    private String userEmail;
    @JsonProperty("userType")
    private String userType;
}
