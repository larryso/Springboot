create sequence USER_SEQ
    increment by 50
    minvalue 51
    maxvalue 9999999

create table "USER"
(
    "USER_PK" numeric not null PRIMARY KEY,
    "USER_ID"  varchar(255) not null,
    "NAME" varchar(255) not null,
    "EMAIL" VARCHAR(255) not null,
    "PASSWORD" VARCHAR(50) not null
)
