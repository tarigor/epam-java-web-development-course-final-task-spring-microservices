package com.epam.requestorderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Subselect("select room.id,persons_in_room,room_cost,room_class,room_image_link\n" +
        "from room join room_class rc on room.room_class_id = rc.id")
public class RoomData {
    @Id
    private Integer id;
    private Integer personsInRoom;
    private Double roomCost;
    private String roomClass;
    private String roomImageLink;
}
