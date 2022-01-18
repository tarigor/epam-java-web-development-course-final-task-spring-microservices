package com.epam.requestorderservice.repository;

import com.epam.requestorderservice.entity.Requests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Requests,Integer> {
}
