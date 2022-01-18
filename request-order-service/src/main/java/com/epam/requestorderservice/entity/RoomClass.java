package com.epam.requestorderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String roomClass;
    private String roomImageLink;
    @OneToOne(mappedBy = "roomClass")
    private Room room;
}
