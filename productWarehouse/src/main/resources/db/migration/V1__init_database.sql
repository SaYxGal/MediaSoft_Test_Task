create table if not exists products
(
    id uuid not null primary key,
    name varchar(255) not null,
    part_number varchar(255) not null unique,
    description varchar(255) not null,
    category varchar(255) not null,
    price double precision not null,
    count integer not null,
    last_count_change timestamp without time zone not null,
    date_of_creation date not null
);