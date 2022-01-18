package com.epam.requestorderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Subselect("" +
        "select order_id,\n" +
        "       client_id,\n" +
        "       request_id,\n" +
        "       room_id,\n" +
        "       room_class,\n" +
        "       check_in_date,\n" +
        "       check_out_date,\n" +
        "       order_status\n" +
        "from orders\n" +
        "         join room r on orders.room_id = r.id\n" +
        "         join room_class rc on r.room_class_id = rc.id")
public class ClientOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    private long clientId;
    private Integer requestId;
    private Integer roomId;
    private String roomClass;
    private Date checkInDate;
    private Date checkOutDate;
    private String orderStatus;
}
