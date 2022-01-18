package com.epam.requestorderservice;

import com.epam.requestorderservice.entity.ClientOrders;
import com.epam.requestorderservice.repository.ClientOrdersRepository;
import com.epam.requestorderservice.repository.RoomDataRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

@SpringBootTest
class RequestOrderServiceApplicationTests {

    @Autowired
    ClientOrdersRepository clientOrdersRepository;

    @Test
    void contextLoads() {
        clientOrdersRepository.getClientOrdersByClientId(1L).forEach(System.out::println);
    }

}
