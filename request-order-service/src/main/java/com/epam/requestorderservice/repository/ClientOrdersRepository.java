package com.epam.requestorderservice.repository;

import com.epam.requestorderservice.entity.ClientOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientOrdersRepository extends JpaRepository<ClientOrders, Integer> {
    List<ClientOrders> getClientOrdersByClientId(Long clientId);
}
