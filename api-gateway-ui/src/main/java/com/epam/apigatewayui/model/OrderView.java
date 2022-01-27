package com.epam.apigatewayui.model;

import com.epam.apigatewayui.types.RequestOrderStatus;

public class OrderView {
    private final Orders order;
    private final String orderStatusDescription;
    private final boolean paymentRequired;

    public OrderView(Orders order) {
        this.order = order;
        this.orderStatusDescription = RequestOrderStatus.valueOf(order.getOrderStatus()).getDescription();
        this.paymentRequired = order.getOrderStatus().equals(RequestOrderStatus.APPROVED_WAITING_FOR_PAYMENT.name());
    }

    public Orders getOrder() {
        return order;
    }

    public String getOrderStatusDescription() {
        return orderStatusDescription;
    }

    public boolean isPaymentRequired() {
        return paymentRequired;
    }

    @Override
    public String toString() {
        return "OrderView{" +
                "order=" + order +
                ", orderStatusDescription='" + orderStatusDescription + '\'' +
                ", paymentRequired=" + paymentRequired +
                '}';
    }
}
