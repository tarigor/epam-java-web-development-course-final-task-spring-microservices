package com.epam.requestorderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailToAdmin {
    private User user;
    private int requestID;
    private int persons;
    private String roomClass;
    private String dateFrom;
    private String dateTo;
}
