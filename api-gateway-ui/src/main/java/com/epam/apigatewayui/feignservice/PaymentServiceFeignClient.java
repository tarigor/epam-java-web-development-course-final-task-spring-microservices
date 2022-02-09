package com.epam.apigatewayui.feignservice;

import com.epam.apigatewayui.feignservice.fallback.PaymentServiceFeignClientFactoryFallback;
import com.epam.apigatewayui.model.Order;
import com.epam.apigatewayui.model.PaymentDetails;
import com.paypal.api.payments.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Primary
@FeignClient(name = "PAYMENT-SERVICE", url = "http://localhost:8090", fallbackFactory = PaymentServiceFeignClientFactoryFallback.class)
public interface PaymentServiceFeignClient {

    @PostMapping(value = "/create-payment", consumes = "application/json")
    ResponseEntity<Payment> createPayment(@RequestBody Order order);

    @PostMapping(value = "/do-payment", consumes = "application/json")
    ResponseEntity<Payment> executePayment(@RequestBody PaymentDetails paymentDetails);

    @PostMapping(value = "/test", consumes = "application/json")
    ResponseEntity<String> test(String text);
}
