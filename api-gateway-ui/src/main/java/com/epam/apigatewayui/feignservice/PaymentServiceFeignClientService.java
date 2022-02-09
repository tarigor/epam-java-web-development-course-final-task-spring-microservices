package com.epam.apigatewayui.feignservice;

import com.epam.apigatewayui.model.Order;
import com.epam.apigatewayui.model.PaymentDetails;
import com.paypal.api.payments.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentServiceFeignClientService {

    @Autowired
    private PaymentServiceFeignClient paymentServiceFeignClient;

    public ResponseEntity<Payment> createPayment(double price, String currency, String method, String intent, String description, String cancelUrl, String successUrl) {
        log.info("rest sent");
        ResponseEntity<Payment> paymentResponseEntity = paymentServiceFeignClient.createPayment(new Order(
                price,
                currency,
                method,
                intent,
                description,
                cancelUrl,
                successUrl
        ));
        log.info("rest received");
        System.out.println("payment response entity -> " + paymentResponseEntity.toString());
        return paymentResponseEntity;
    }

    public ResponseEntity<Payment> executePayment(String paymentId, String payerId) {
        return paymentServiceFeignClient.executePayment(new PaymentDetails(paymentId, payerId));
    }

    public ResponseEntity<String> test(String text) {
        ResponseEntity<String> testt = paymentServiceFeignClient.test(text);
        return testt;
    }
}
