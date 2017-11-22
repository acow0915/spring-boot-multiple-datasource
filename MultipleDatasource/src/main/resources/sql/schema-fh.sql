drop table PT_GAME_BET_RECORD purge;

CREATE TABLE PT_GAME_BET_RECORD (
  ID number(10, 0) not null,
  USER_ID number(10, 0),
  ACCOUNT varchar2(100),
  GAME_ID varchar2(20) ,
  SN varchar2(45) NOT NULL ,
  CURRENTBET number(20, 0) NOT NULL ,
  PRIZE number(20, 0) NOT NULL ,
  PT_ACCOUNT varchar2(45) NOT NULL ,
  GAME_NAME varchar2(100) ,
  GMT_CREATE timestamp NOT NULL ,
  GMT_WRITE timestamp NOT NULL ,
  STATUS number(4, 0) DEFAULT 0,
  AGENT_TYPE number(4, 0) DEFAULT 0,
  WIN number(4, 0) DEFAULT 0,
  BALANCE number(20, 0) NOT NULL ,
  GAME_RULE number(11, 0) ,
  PT_ONLINE number(4, 0) ,
  PROGRESSIVEBET number(20, 0) ,
  PROGRESSIVEWIN number(20, 0) ,
  UNIQUE (SN)
);

ALTER TABLE PT_GAME_BET_RECORD ADD CONSTRAINT PT_GAME_BET_RECORD_PK PRIMARY KEY (ID);