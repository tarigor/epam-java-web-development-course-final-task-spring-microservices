package com.epam.requestorderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailToClient {
    private User user;
    private int requestID;
    private int orderAssigned;
}
