package com.epam.apigatewayui.model;

import com.epam.apigatewayui.types.RoomClassType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomData {
    private Integer id;
    private Integer personsInRoom;
    private Double roomCost;
    private RoomClassType roomClass;
    private String roomImageLink;
}
