package com.epam.apigatewayui.model;

import com.epam.apigatewayui.types.RequestOrderStatus;

public class OrderView {
    private final Order order;
    private final String orderStatusDescription;
    private final boolean paymentRequired;

    public OrderView(Order order) {
        this.order = order;
        this.orderStatusDescription = RequestOrderStatus.valueOf(order.getOrderStatus()).getDescription();
        this.paymentRequired = order.getOrderStatus().equals(RequestOrderStatus.WAITING_FOR_APPROVAL.name());
    }

    public Order getOrder() {
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
