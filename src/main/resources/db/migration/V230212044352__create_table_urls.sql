create table urls
(
    id         bigserial not null
        constraint pk_switchers_urls primary key,
    source_id  int       not null,
    constraint fk_urls_sources
        foreign key (source_id)
            references sources (id),
    link       varchar   not null,
    created_at timestamp default current_timestamp(0),
    updated_at timestamp default null
)