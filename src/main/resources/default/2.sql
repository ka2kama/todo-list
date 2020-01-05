--- Sample dataset

--- !Ups

insert into todo (id, content, state) values (1,'掃除', 0);
insert into todo (id, content, state) values (2,'洗濯', 0);
insert into todo (id, content, state) values (3,'料理', 0);

--- !Downs

delete from todo;
