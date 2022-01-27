package com.epam.requestorderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusModifier {
    private String orderStatus;
    private Integer orderID;
}
