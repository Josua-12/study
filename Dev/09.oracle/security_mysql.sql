create database securitydb
	character set utf8mb4
	collate utf8mb4_general_ci;

insert into `role` (id, name )
values (1, 'USER');

insert into `role` (id, name )
values (2, 'MANAGER');

insert into `role` (id, name )
values (3, 'ADMIN');

create database librarydb
	character set utf8mb4
	collate utf8mb4_general_ci;
