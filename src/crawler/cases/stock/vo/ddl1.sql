drop user wkai cascade;
CREATE USER wkai IDENTIFIED BY wkai ;
GRANT DBA TO wkai;
commit;


create table STOCKINFO
(
  code         VARCHAR2(6) not null primary key,
  listedDate DATE,
  name        VARCHAR2(32)
);