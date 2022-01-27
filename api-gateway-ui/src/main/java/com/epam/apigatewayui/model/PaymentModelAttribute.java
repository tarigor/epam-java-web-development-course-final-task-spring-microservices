package com.epam.apigatewayui.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentModelAttribute {
    private String clientID;
    private String orderID;
    private String requestID;
    private String roomID;
    private String roomPrice;
}
