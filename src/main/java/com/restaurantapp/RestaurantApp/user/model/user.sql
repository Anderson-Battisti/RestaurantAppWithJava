CREATE TABLE users (
       id serial primary key,
       username varchar(50) not null,
       password TEXT not null,
       active boolean not null);