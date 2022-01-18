package com.epam.requestorderservice.service;

import com.epam.requestorderservice.entity.RoomData;
import com.epam.requestorderservice.repository.RoomDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomDataRepository roomDataRepository;

    public List<RoomData> getAllRoomsData(){
        return roomDataRepository.findAll();
    }
}
