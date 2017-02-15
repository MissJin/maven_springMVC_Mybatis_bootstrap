drop database if exists mavendemo;
create database mavendemo;
use mavendemo;
drop table if exists user;
create table user(
id int primary key auto_increment,
name varchar(20) default null,
mobile varchar(20) default null,
create_time datetime default null
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

insert into user(name,mobile,create_time) values ("test","14787895215",now());