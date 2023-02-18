create table items
(
    id          bigserial not null
        constraint pk_items_id primary key,
    name        varchar   not null,
    price       varchar   not null,
    description varchar,
    img         varchar,
    link        varchar,
    source_id   int       not null,
    constraint fk_items_sources
        foreign key (source_id)
            references sources (id),
    user_id     int       not null,
    constraint fk_items_users
        foreign key (user_id)
            references users (id),
    created_at  timestamp default current_timestamp(0),
    updated_at  timestamp default null
);
