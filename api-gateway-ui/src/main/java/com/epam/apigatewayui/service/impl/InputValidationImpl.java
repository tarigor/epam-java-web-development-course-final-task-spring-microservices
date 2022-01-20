package com.epam.apigatewayui.service.impl;

import com.epam.apigatewayui.service.IInputValidation;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

@Component
public class InputValidationImpl implements IInputValidation {
    public static final String PASSWORD_DOUBLE_CHECK = "passwordDoubleCheck";
    private ArrayList<String> fieldErrorList = new ArrayList<>();

    @Override
    public ArrayList<String> doValidation(BindingResult bindingResult) {

        List<ObjectError> errorList = bindingResult.getAllErrors();
        FieldError fieldError;
        for (ObjectError error : errorList) {
            if (error instanceof FieldError) {
                fieldError = (FieldError) error;
                fieldErrorList.add(fieldError.getField());
            }
        }
        return fieldErrorList;
    }

    /**
     * Validates an inputted password a twice.
     *
     * @param password         an inputted password.
     * @param repeatedPassword a second time an inputted password.
     * @return a boolean result of the passwords comparision.
     */
    @Override
    public boolean validatePasswordTwice(String password, String repeatedPassword) {

        fieldErrorList.clear();
        if (!password.equals(repeatedPassword)) {
            fieldErrorList.add(PASSWORD_DOUBLE_CHECK);
        }
        return password.equals(repeatedPassword);
    }
}
