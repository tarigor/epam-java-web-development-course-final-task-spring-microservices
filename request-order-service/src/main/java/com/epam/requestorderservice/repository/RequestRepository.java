package com.epam.requestorderservice.repository;

import com.epam.requestorderservice.entity.Requests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Requests,Long> {
    List<Requests> getRequestsByClientId(Long clientId);
}
