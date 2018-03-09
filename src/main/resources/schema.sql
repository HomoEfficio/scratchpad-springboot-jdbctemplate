create table member (
  id bigint auto_increment,
  email varchar(255) not null,
  name varchar(255) not null,
  primary key (id)
);

create table member_impala (
  id bigint auto_increment,
  email varchar(255) not null,
  name varchar(255) not null,
  primary key (id)
);
