package com.epam.apigatewayui.feignservice;

import com.epam.apigatewayui.model.Order;
import com.epam.apigatewayui.model.PaymentDetails;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PAYMENT-SERVICE", url = "http://localhost:8090")
public interface PaymentServiceFeignClient {

    @PostMapping(value = "/create-payment", consumes = "application/json")
    Payment createPayment(@RequestBody Order order) throws PayPalRESTException;

    @PostMapping(value = "/do-payment", consumes = "application/json")
    Payment executePayment(@RequestBody PaymentDetails paymentDetails);
}
