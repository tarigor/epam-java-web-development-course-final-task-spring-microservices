package com.epam.apigatewayui.controller;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ClientControllerTest {

    @Test
    void getOrderIdFromMessage() {
        String message = "Payment for room booking as per order#21";

        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(message);

        while (m.find()) {
            System.out.println(m.group());
        }
    }
}