/*create database demomicroservice;*/
/*
use demomicroservice;
show tables;
*/
drop table if exists `clients`^
create table clients(
	  id bigint not null AUTO_INCREMENT,
    nit INT(11),
    reasonsocial varchar(100),
    primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8^
/*
CREATE UNIQUE INDEX id_unique_nit ON clients(nit);
ALTER TABLE clients DROP INDEX id_unique_nit;
DROP INDEX id_unique_nit ON clients;
*/
/*
select * from clients;
desc clients;
*/

/*
insert into clients (nit,reasonsocial)
values(6405588,'Carlos');

insert into clients (nit,reasonsocial)
values(6505588,'Juan Carlos');

--error, because this nit is duplicated
insert into clients (nit,reasonsocial)
values(6405588,'Juan Carlos');
*/
