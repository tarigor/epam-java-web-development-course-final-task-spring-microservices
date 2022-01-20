package com.epam.apigatewayui.model;

import com.epam.apigatewayui.types.RequestOrderStatus;

public class RequestView {
    private final Request request;
    private final String requestStatusDescription;
    private final boolean isProcessed;

    public RequestView(Request request) {
        this.request = request;
        this.requestStatusDescription = RequestOrderStatus.valueOf(request.getRequestStatus()).getDescription();
        isProcessed = request.getRequestStatus().equals(RequestOrderStatus.WAITING_FOR_APPROVAL.name());
    }

    public Request getRequest() {
        return request;
    }

    public String getRequestStatusDescription() {
        return requestStatusDescription;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    @Override
    public String toString() {
        return "RequestView{" +
                "request=" + request +
                ", requestStatusDescription='" + requestStatusDescription + '\'' +
                ", isProcessed=" + isProcessed +
                '}';
    }
}
