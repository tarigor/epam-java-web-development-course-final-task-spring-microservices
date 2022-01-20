package com.epam.commonuserservice.model;

import lombok.Value;


@Value
public class UserDataWhileLogin {
    String userEmail;
    String userPassword;
}
