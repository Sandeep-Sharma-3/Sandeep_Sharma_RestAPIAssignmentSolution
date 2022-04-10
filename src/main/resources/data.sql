insert into employee values (1, 'a','a','a');
insert into employee values (2, 'a','ab','abb');
insert into employee values (3, 'b','b','b');
insert into employee values (4, 'c','c','c');
insert into employee values (5, 'c','cc','ccc');
insert into employee values (6, 'd','d','d');

insert into role values (1, 'ADMIN');
insert into role values (2, 'USER');

insert into users (id, username, password) values (1, 'sandeep', '$2a$10$PXk9eaqe.wAU6IHHsOTPzOAxKPtRO.Z5SFpHwKbBNprfkpueGRdCq');
insert into users_roles values (1,1);

insert into users (id, username, password) values (2, 'sandeep2', '$2a$10$2QHDmYNrfD/QHVZkujURq.5hX2tgB1MI7MLrisIAau/0HGLTffhCa');
insert into users_roles values (2,2);

