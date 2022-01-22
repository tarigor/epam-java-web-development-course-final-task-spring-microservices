package com.epam.requestorderservice.repository;

import com.epam.requestorderservice.entity.Requests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Requests, Long> {
    List<Requests> getRequestsByClientId(Long clientId);

    @Transactional
    @Modifying
    @Query("UPDATE Requests r SET r.requestStatus= :requestStatus WHERE r.requestId = :requestID ")
    void changeRequestStatus(@Param("requestStatus") String requestStatus, @Param("requestID") Integer requestID);
}
