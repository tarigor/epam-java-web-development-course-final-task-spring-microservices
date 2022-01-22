package com.epam.requestorderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDataWhileInsert {
    private String requestID;
    private String clientID;
    private String[] singleRoomsSelected;
    private String[] doubleRoomsSelected;
    private String[] suiteRoomsSelected;
    private String[] deluxeRoomsSelected;
    private String dateFrom;
    private String dateTo;
}
