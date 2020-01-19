--- First database schema

--- !Ups

set ignorecase true;

create table todo (
  id                        bigint not null,
  content                   varchar(255) not null,
  state                     integer not null,
  constraint pk_todo primary key (id))
;

create sequence todo_seq start with 1000;

--- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists todo;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists todo_seq;
