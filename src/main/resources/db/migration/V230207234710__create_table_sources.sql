create table sources
(
    id         bigserial not null
        constraint pk_sources_id primary key,
    name       varchar   not null,
    created_at timestamp default current_timestamp(0),
    updated_at timestamp default null
);