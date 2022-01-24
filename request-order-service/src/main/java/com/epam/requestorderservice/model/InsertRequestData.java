package com.epam.requestorderservice.model;

import com.epam.requestorderservice.entity.Requests;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InsertRequestData {
    private User user;
    private Requests request;
}
