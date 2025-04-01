--liquibase formatted sql
--changeset creator:100 endDelimiter:go
-- this script is for SQL Server and Azure SQL

create table ACTOR_PROFILE
(
    ACTOR_PROFILE_PK numeric(18) not null primary key,
    REGION varchar(255),
    OFFICE_LOCATION varchar(255),
    IP_RANGE varchar(255)
)
go

CREATE SEQUENCE [dbo].[ACTOR_PROFILE_SEQ]
AS [bigint]
START WITH 1
INCREMENT BY 50
MINVALUE 1
MAXVALUE 99999
CACHE
GO

