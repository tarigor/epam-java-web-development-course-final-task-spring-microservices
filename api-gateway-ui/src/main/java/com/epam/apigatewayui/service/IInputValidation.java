package com.epam.apigatewayui.service;

import org.springframework.validation.BindingResult;

import java.util.ArrayList;

public interface IInputValidation {
    ArrayList<String> doValidation(BindingResult bindingResult);

    boolean validatePasswordTwice(String password, String repeatedPassword);
}
