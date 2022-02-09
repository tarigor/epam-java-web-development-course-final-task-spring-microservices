package com.epam.requestorderservice.repository;

import com.epam.requestorderservice.entity.RoomData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomDataRepository extends JpaRepository<RoomData, Integer> {
}
