package com.epam.apigatewayui.model;

import com.epam.apigatewayui.types.RequestOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestView {
    private long requestID;
    private long clientID;
    private String firstName;
    private String lastName;
    private String email;
    private int persons;
    private String roomClass;
    private Date dateFrom;
    private Date dateTo;
    private RequestOrderStatus requestOrderStatus;
    private String requestSentTime;
    private boolean processed;

    public ClientRequestView(
            long requestID,
            long clientID,
            String firstName,
            String lastName,
            String email,
            int persons,
            String roomClass,
            Date dateFrom,
            Date dateTo,
            RequestOrderStatus requestOrderStatus,
            String requestSentTime) {
        this.requestID = requestID;
        this.clientID = clientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.persons = persons;
        this.roomClass = roomClass;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.requestOrderStatus = requestOrderStatus;
        if (requestOrderStatus.equals(RequestOrderStatus.WAITING_FOR_APPROVAL)) {
            setProcessed(true);
        }
        this.requestSentTime = requestSentTime;
    }
}
