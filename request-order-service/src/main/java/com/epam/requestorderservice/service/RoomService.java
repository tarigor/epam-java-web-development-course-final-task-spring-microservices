package com.epam.requestorderservice.service;

import com.epam.requestorderservice.entity.RoomData;
import com.epam.requestorderservice.entity.RoomView;
import com.epam.requestorderservice.repository.RoomDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.List;

@Service
public class RoomService {

    private static final String SQL_GET_FREE_ROOMS = "call get_free_rooms(:dateFrom,:dateTo)";

    @Autowired
    private RoomDataRepository roomDataRepository;
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<RoomData> getAllRoomsData() {
        return roomDataRepository.findAll();
    }

    public List<RoomView> getFreeRooms(Date dateFromSQL, Date dateToSQL) {

        return jdbcTemplate.query(SQL_GET_FREE_ROOMS, new MapSqlParameterSource()
                        .addValue("dateFrom", dateFromSQL)
                        .addValue("dateTo", dateToSQL),
                (resultSet, rowNum) -> new RoomView(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                ));
    }
}
