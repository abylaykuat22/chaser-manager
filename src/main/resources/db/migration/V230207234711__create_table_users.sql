create table users
(
    id         bigserial not null
        constraint pk_users_id primary key,
    email      varchar   not null,
    password   varchar   not null,
    created_at timestamp default current_timestamp(0),
    updated_at timestamp default null
);

insert into users(email, password)
values ('marat_bh@mail.ru', 'qwe')