package com.epam.apigatewayui.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomView {
    private Integer roomID;
    private String roomType;
}
