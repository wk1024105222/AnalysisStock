-- Create table
create table INCOME
(
  id           VARCHAR2(32) not null,
  code         VARCHAR2(6) not null,
  release_date DATE,
  yyzsr        NUMBER(17,2),
  yyzcb        NUMBER(17,2),
  tzsy         NUMBER(17,2),
  yylr         NUMBER(17,2),
  jlr          NUMBER(17,2),
  mgsgdjlr     NUMBER(17,2),
  ssgdsy       NUMBER(17,2)
);
-- Add comments to the columns 
comment on column INCOME.yyzsr
  is '营业总收入';
comment on column INCOME.yyzcb
  is '营业总成本';
comment on column INCOME.tzsy
  is '投资收益';
comment on column INCOME.yylr
  is '营业利润';
comment on column INCOME.jlr
  is '净利润';
comment on column INCOME.mgsgdjlr
  is '归属于母公司所有者的净利润
';
comment on column INCOME.ssgdsy
  is '少数股东损益
';
-- Create/Recreate primary, unique and foreign key constraints 
alter table INCOME
  add primary key (ID);
-- Create/Recreate indexes 
create unique index INDEX_INCOME on INCOME (CODE, RELEASE_DATE);
--------------------------------------------------------------------------------------
-- Create table
create table BALANCE
(
  id           VARCHAR2(32) not null,
  code         VARCHAR2(6) not null,
  release_date DATE,
  ldzchj       NUMBER(17,2),
  fldzchj      NUMBER(17,2),
  zchj         NUMBER(17,2),
  ldfzhj       NUMBER(17,2),
  fldfzhj      NUMBER(17,2),
  fzhj         NUMBER(17,2),
  syzqy        NUMBER(17,2),
  mgsgdqy      NUMBER(17,2),
  ssgdqy       NUMBER(17,2)
);
-- Add comments to the columns 
comment on column BALANCE.ldzchj
  is '流动资产合计';
comment on column BALANCE.fldzchj
  is '非流动资产合计';
comment on column BALANCE.zchj
  is '资产总计
';
comment on column BALANCE.ldfzhj
  is '流动负债合计
';
comment on column BALANCE.fldfzhj
  is '非流动负债合计
';
comment on column BALANCE.fzhj
  is '负债合计
';
comment on column BALANCE.syzqy
  is '所有者权益
';
comment on column BALANCE.mgsgdqy
  is '归属于母公司股东权益合计
';
comment on column BALANCE.ssgdqy
  is '少数股东权益
';
-- Create/Recreate primary, unique and foreign key constraints 
alter table BALANCE
  add primary key (ID);
-- Create/Recreate indexes 
create unique index INDEX_BALANCE on BALANCE (CODE, RELEASE_DATE);
----------------------------------------------------------------------------------------
-- Create table
create table CASHFLOW
(
  id           VARCHAR2(32) not null,
  code         VARCHAR2(6) not null,
  release_date DATE,
  jyxjlr       NUMBER(17,2),
  jyxjlc       NUMBER(17,2),
  jyxjje       NUMBER(17,2),
  tzxjlr       NUMBER(17,2),
  tzxjlc       NUMBER(17,2),
  tzxjje       NUMBER(17,2),
  czxjlr       NUMBER(17,2),
  czxjlc       NUMBER(17,2),
  czxjje       NUMBER(17,2),
  xjzje        NUMBER(17,2),
  qcxjye       NUMBER(17,2),
  qmxjye       NUMBER(17,2)
);
-- Add comments to the columns 
comment on column CASHFLOW.jyxjlr
  is '经营活动现金流入小计
';
comment on column CASHFLOW.jyxjlc
  is '经营活动现金流出小计
';
comment on column CASHFLOW.jyxjje
  is '经营活动产生的现金流量净额
';
comment on column CASHFLOW.tzxjlr
  is '投资活动现金流入小计
';
comment on column CASHFLOW.tzxjlc
  is '投资活动现金流出小计
';
comment on column CASHFLOW.tzxjje
  is '投资活动产生的现金流量净额
';
comment on column CASHFLOW.czxjlr
  is '筹资活动现金流入小计
';
comment on column CASHFLOW.czxjlc
  is '筹资活动现金流出小计
';
comment on column CASHFLOW.czxjje
  is '筹资活动产生的现金流量净额
';
comment on column CASHFLOW.xjzje
  is '现金及现金等价物净增加额
';
comment on column CASHFLOW.qcxjye
  is '期初现金及现金等价物余额
';
comment on column CASHFLOW.qmxjye
  is '期末现金及现金等价物余额
';
-- Create/Recreate primary, unique and foreign key constraints 
alter table CASHFLOW
  add primary key (ID);
-- Create/Recreate indexes 
create unique index INDEX_CASHFLOW on CASHFLOW (CODE, RELEASE_DATE);
