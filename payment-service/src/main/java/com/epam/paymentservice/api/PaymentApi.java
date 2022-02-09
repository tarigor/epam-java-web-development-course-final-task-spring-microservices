package com.epam.paymentservice.api;

import com.epam.paymentservice.model.Order;
import com.epam.paymentservice.model.PaymentDetails;
import com.epam.paymentservice.service.PaypalService;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentApi {

    @Autowired
    private PaypalService paypalService;

    @PostMapping("/create-payment")
    public ResponseEntity<Payment> createPayment(@RequestBody Order order) throws PayPalRESTException {
        System.out.println("in create payment - CREATE PAYMENT MICROSERVICE");
        System.out.println("order -> " + order.toString());
        Payment payment = paypalService.createPayment(
                order.getPrice(),
                order.getCurrency(),
                order.getMethod(),
                order.getIntent(),
                order.getDescription(),
                order.getCancelUrl(),
                order.getSuccessUrl());
        System.out.println("payment -> " + payment.toString());
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @PostMapping("/do-payment")
    public ResponseEntity<Payment> executePayment(@RequestBody PaymentDetails paymentDetails) throws PayPalRESTException {
        System.out.println("in do payment");
        Payment payment = paypalService.executePayment(paymentDetails.getPaymentId(), paymentDetails.getPayerId());
        System.out.println("payment in dopayment -> " + payment);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @GetMapping(value = "/test/{text}")
    ResponseEntity<String> test(@PathVariable String text) {
        return new ResponseEntity<String>(text, HttpStatus.OK);
    }
}
