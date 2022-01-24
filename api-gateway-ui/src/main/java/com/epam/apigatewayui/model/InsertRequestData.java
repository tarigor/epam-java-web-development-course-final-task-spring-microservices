package com.epam.apigatewayui.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InsertRequestData {
    private User user;
    private Request request;
}
