create table phone_entity (
    id bigint not null,
    city_code integer,
    country_code varchar(255),
    number bigint,
    primary key (id)
)

create table user_entity (
    id uuid not null,
    created timestamp,
    email varchar(255),
    is_active boolean,
    last_login timestamp,
    name varchar(255),
    password varchar(255),
    token varchar(255),
    primary key (id)
)

create table user_entity_phones (
    user_entity_id uuid not null,
    phones_id bigint not null
)

alter table user_entity_phones drop constraint if exists UK_edppdeu8iqiy730abiifi9s90

alter table user_entity_phones add constraint UK_edppdeu8iqiy730abiifi9s90 unique (phones_id)

create sequence hibernate_sequence start with 1 increment by 1

alter table user_entity_phones add constraint FKkdiuag68dtvwneuons07b8w7h foreign key (phones_id) references phone_entity

alter table user_entity_phones add constraint FKhnfxnktr1rlripfywjp15i0v8 foreign key (user_entity_id) references user_entity