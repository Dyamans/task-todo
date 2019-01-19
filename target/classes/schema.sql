create table todoitem
(
   id integer not null,
   text varchar(50) not null,
   isCompleted boolean not null,
   createdAt timestamp not null,
   primary key(id)
);