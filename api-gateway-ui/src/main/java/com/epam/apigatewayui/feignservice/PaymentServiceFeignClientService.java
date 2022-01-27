package com.epam.apigatewayui.feignservice;

import com.epam.apigatewayui.model.Order;
import com.epam.apigatewayui.model.PaymentDetails;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceFeignClientService {

    @Autowired
    private PaymentServiceFeignClient paymentServiceFeignClient;

    public Payment createPayment(double price, String currency, String method, String intent, String description, String cancelUrl, String successUrl) throws PayPalRESTException {
        return paymentServiceFeignClient.createPayment(new Order(
                price,
                currency,
                method,
                intent,
                description,
                cancelUrl,
                successUrl
        ));
    }

    public Payment executePayment(String paymentId, String payerId) {
        return paymentServiceFeignClient.executePayment(new PaymentDetails(paymentId, payerId));
    }
}
