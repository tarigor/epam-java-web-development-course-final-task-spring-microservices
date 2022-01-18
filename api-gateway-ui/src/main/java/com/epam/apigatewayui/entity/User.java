package com.epam.apigatewayui.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Objects;

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
