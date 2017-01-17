CREATE sequence POKEMON_ID_SEQUENCE start with 10000;
CREATE sequence USER_ROLE_ID_SEQUENCE start with 1;

CREATE TABLE PORTAL_USER (
  USER_NAME VARCHAR(45) NOT NULL,
  PASSWORD VARCHAR(60) NOT NULL,
  ENABLED number(1,0) DEFAULT 1
);

alter TABLE PORTAL_USER add (
  constraint PK_PORTAL_USER primary key (USER_NAME)
);

CREATE TABLE PORTAL_USER_ROLES (
  USER_ROLE_ID number(11,0) NOT NULL,
  USER_NAME varchar(45) NOT NULL,
  ROLE varchar(45) NOT NULL
);

alter TABLE PORTAL_USER_ROLES add (
  constraint PK_PORTAL_USER_ROLES primary key (USER_ROLE_ID),
  constraint FK_PORTAL_USER foreign key (USER_NAME) references PORTAL_USER(USER_NAME),
  constraint ROLE_USER unique (USER_ROLE_ID, USER_NAME)
);

CREATE TABLE POKEMON (
  ID number(19,0) not null,

	NAME varchar2(100) not null,
  TYPE varchar2(50) not null,
  COLOR varchar2(50) not null,
  USER_NAME VARCHAR(45) NOT NULL
);

alter TABLE POKEMON add (
  constraint PK_POKEMON primary key (ID),
  constraint FK_POKEMON_PORTAL_USER foreign key (USER_NAME) references PORTAL_USER(USER_NAME)
);