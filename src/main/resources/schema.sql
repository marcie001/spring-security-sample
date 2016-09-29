create table if not exists users (
    id serial not null primary key,
    fullname varchar(255) not null,
    account varchar(255) not null unique,
    password char(60) not null,
    role varchar(10) not null
);
