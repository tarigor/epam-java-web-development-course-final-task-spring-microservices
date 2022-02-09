create table hibernate_sequence
(
    next_val bigint null
);

create table orders
(
    order_id               int          not null
        primary key,
    request_id             int          null,
    client_id              bigint       null,
    room_id                int          null,
    check_in_date          date         null,
    check_out_date         date         null,
    order_status           varchar(255) null,
    invoice_sent_time      varchar(255) null,
    payment_receiving_time varchar(255) null
);

create table requests
(
    request_id        int          not null
        primary key,
    check_in_date     date         null,
    check_out_date    date         null,
    client_id         bigint       not null,
    persons_amount    int          null,
    request_status    varchar(255) null,
    room_class        varchar(255) null,
    request_sent_time varchar(255) null
);

create table room_class
(
    id              int          not null
        primary key,
    room_class      varchar(255) null,
    room_image_link varchar(255) null
);

create table room
(
    id              int    not null
        primary key,
    persons_in_room int    null,
    room_cost       double null,
    room_class_id   int    null,
    constraint FKq01r409lq281ymapjlkul3ypc
        foreign key (room_class_id) references room_class (id)
);

create
    definer = administrator@localhost procedure get_free_rooms(IN dateFrom date, IN dateTo date)
BEGIN
    drop temporary table if exists temp1;
    create temporary table if not exists temp1
    select distinct room_id
    from `orders`
    where dateFrom between check_in_date and check_out_date
       or dateTo between check_in_date and check_out_date
       or dateFrom < check_in_date and dateTo > check_out_date;

    select r.id, rc.room_class
    from temp1 t1
             right join room r on t1.room_id = r.id
             join room_class rc on r.room_class_id = rc.id
    where t1.room_id is null
    order by id;
END;


