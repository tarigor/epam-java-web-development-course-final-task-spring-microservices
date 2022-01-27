package com.epam.requestorderservice.repository;

import com.epam.requestorderservice.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE Orders o SET o.orderStatus= :orderStatus WHERE o.orderId= :orderID ")
    void changeOrderStatus(@Param("orderStatus") String orderStatus, @Param("orderID") Integer orderID);
}
