create table switchers
(
    id          bigserial not null
        constraint pk_switchers_id primary key,
    user_id     int       not null,
    constraint fk_switchers_users
        foreign key (user_id)
            references users (id),
    url_id      int       not null,
    constraint fk_switchers_urls
        foreign key (url_id)
            references urls (id),
    status      varchar   not null,
    periodicity bigint    not null,
    name        varchar   not null,
    created_at  timestamp default current_timestamp(0),
    updated_at  timestamp default null
)