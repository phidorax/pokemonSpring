CREATE TABLE IF NOT EXISTS ability
(
    id          bigserial primary key,
    description varchar(255),
    name        varchar(255)
);

CREATE TABLE IF NOT EXISTS move
(
    id          bigserial primary key,
    accuracy    integer,
    description varchar(255),
    name        varchar(255),
    power       integer,
    pp          integer
);

CREATE TABLE IF NOT EXISTS pokemon
(
    id              bigserial primary key,
    attack          integer,
    defense         integer,
    hp              integer,
    name            varchar(255),
    special_attack  integer,
    special_defense integer,
    speed           integer
);

CREATE TABLE IF NOT EXISTS type
(
    id      bigserial primary key,
    name    varchar(255),
    move_id bigint
        constraint fkl5dogoa35m0axawwm29gl504e references move
);

CREATE TABLE IF NOT EXISTS move_type
(
    move_entity_id bigint not null
        constraint fk89kmp6imjxitg3dwo2obnisuh references move,
    type_id        bigint not null
        constraint uk_95fxjjdu7qcsw18q4vsw0bs1w unique
        constraint fkds642av1q97m809fm8acybbet references type
);

CREATE TABLE IF NOT EXISTS pokemon_type
(
    pokemon_id bigint not null
        constraint fkjii6ffk1wg2utlg0ex7cg3uqy references pokemon,
    type_id    bigint not null
        constraint fkn1bw91wtw3yq2ai9hpvb4pa0o references type
);

CREATE TABLE IF NOT EXISTS users
(
    username varchar(50)  not null primary key,
    password varchar(500) not null,
    enabled  boolean      not null
);

CREATE TABLE IF NOT EXISTS authorities
(
    username  varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key
        (username) references users (username)
);

CREATE UNIQUE INDEX ix_auth_username on authorities (username, authority);