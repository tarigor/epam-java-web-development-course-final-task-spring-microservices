package com.epam.requestorderservice;

import com.epam.requestorderservice.repository.RequestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RequestOrderServiceApplicationTests {

    @Autowired
    RequestRepository requestRepository;

    @Test
    void contextLoads() {
        requestRepository.getRequestsByClientId(1l).forEach(System.out::println);
    }

}
