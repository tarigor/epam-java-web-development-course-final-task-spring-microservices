package com.epam.apigatewayui.feignservice.fallback;

import com.epam.apigatewayui.feignservice.PaymentServiceFeignClient;
import com.epam.apigatewayui.model.Order;
import com.epam.apigatewayui.model.PaymentDetails;
import com.paypal.api.payments.Payment;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceFeignClientFactoryFallback implements FallbackFactory<PaymentServiceFeignClient> {
    @Override
    public PaymentServiceFeignClient create(Throwable cause) {
        System.out.println("cause -> " + cause.getMessage());
        return new PaymentServiceFeignClient() {
            @Override
            public ResponseEntity<Payment> createPayment(Order order) {
                return new ResponseEntity<>(new Payment(), HttpStatus.SERVICE_UNAVAILABLE);
            }

            @Override
            public ResponseEntity<Payment> executePayment(PaymentDetails paymentDetails) {
                return new ResponseEntity<>(new Payment(), HttpStatus.SERVICE_UNAVAILABLE);
            }

            @Override
            public ResponseEntity<String> test(String text) {
                return null;
            }
        };
    }
}

