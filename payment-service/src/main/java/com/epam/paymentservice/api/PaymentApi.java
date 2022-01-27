package com.epam.paymentservice.api;

import com.epam.paymentservice.model.Order;
import com.epam.paymentservice.model.PaymentDetails;
import com.epam.paymentservice.service.PaypalService;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentApi {

    @Autowired
    private PaypalService paypalService;

    @PostMapping("/create-payment")
    public Payment createPayment(@RequestBody Order order) throws PayPalRESTException {
        return paypalService.createPayment(
                order.getPrice(),
                order.getCurrency(),
                order.getMethod(),
                order.getIntent(),
                order.getDescription(),
                order.getCancelUrl(),
                order.getSuccessUrl());
    }

    @PostMapping("/do-payment")
    public Payment executePayment(@RequestBody PaymentDetails paymentDetails) throws PayPalRESTException {
        return paypalService.executePayment(paymentDetails.getPaymentId(), paymentDetails.getPayerId());
    }
}
