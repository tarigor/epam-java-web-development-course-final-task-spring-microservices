package com.epam.apigatewayui.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDataWhileLogin {
    private String email;
    private String password;
}
