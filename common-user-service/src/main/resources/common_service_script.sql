create table hibernate_sequence
(
    next_val bigint null
);

create table user
(
    id         bigint       not null
        primary key,
    email      varchar(255) null,
    first_name varchar(255) null,
    last_name  varchar(255) null,
    password   varchar(255) null,
    user_type  varchar(255) null
);


