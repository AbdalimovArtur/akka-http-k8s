drop table if exists books;

create table books
(
    id   bigserial not null
        constraint assemblers_pk
            primary key,
    name varchar
)