package com.epam.requestorderservice.entity;

import com.epam.requestorderservice.type.RequestOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Requests {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer requestId;
    private long clientId;
    private Integer personsAmount;
    private String roomClass;
    private Date checkInDate;
    private Date checkOutDate;
    private String requestStatus;
    private String requestSentTime;
}
