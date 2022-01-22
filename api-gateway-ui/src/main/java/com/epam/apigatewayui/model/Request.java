package com.epam.apigatewayui.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    private Integer requestId;
    private long clientId;
    private Integer personsAmount;
    private String roomClass;
    private Date checkInDate;
    private Date checkOutDate;
    private String requestStatus;
    private String requestSentTime;
}