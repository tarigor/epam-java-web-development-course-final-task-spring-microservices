package com.epam.commonuserservice.model;

import lombok.Value;


@Value
public class UserLoginData {
    String userEmail;
    String userPassword;
}
