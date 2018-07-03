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
  is 'Ӫҵ������';
comment on column INCOME.yyzcb
  is 'Ӫҵ�ܳɱ�';
comment on column INCOME.tzsy
  is 'Ͷ������';
comment on column INCOME.yylr
  is 'Ӫҵ����';
comment on column INCOME.jlr
  is '������';
comment on column INCOME.mgsgdjlr
  is '������ĸ��˾�����ߵľ�����
';
comment on column INCOME.ssgdsy
  is '�����ɶ�����
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
  is '�����ʲ��ϼ�';
comment on column BALANCE.fldzchj
  is '�������ʲ��ϼ�';
comment on column BALANCE.zchj
  is '�ʲ��ܼ�
';
comment on column BALANCE.ldfzhj
  is '������ծ�ϼ�
';
comment on column BALANCE.fldfzhj
  is '��������ծ�ϼ�
';
comment on column BALANCE.fzhj
  is '��ծ�ϼ�
';
comment on column BALANCE.syzqy
  is '������Ȩ��
';
comment on column BALANCE.mgsgdqy
  is '������ĸ��˾�ɶ�Ȩ��ϼ�
';
comment on column BALANCE.ssgdqy
  is '�����ɶ�Ȩ��
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
  is '��Ӫ��ֽ�����С��
';
comment on column CASHFLOW.jyxjlc
  is '��Ӫ��ֽ�����С��
';
comment on column CASHFLOW.jyxjje
  is '��Ӫ��������ֽ���������
';
comment on column CASHFLOW.tzxjlr
  is 'Ͷ�ʻ�ֽ�����С��
';
comment on column CASHFLOW.tzxjlc
  is 'Ͷ�ʻ�ֽ�����С��
';
comment on column CASHFLOW.tzxjje
  is 'Ͷ�ʻ�������ֽ���������
';
comment on column CASHFLOW.czxjlr
  is '���ʻ�ֽ�����С��
';
comment on column CASHFLOW.czxjlc
  is '���ʻ�ֽ�����С��
';
comment on column CASHFLOW.czxjje
  is '���ʻ�������ֽ���������
';
comment on column CASHFLOW.xjzje
  is '�ֽ��ֽ�ȼ��ﾻ���Ӷ�
';
comment on column CASHFLOW.qcxjye
  is '�ڳ��ֽ��ֽ�ȼ������
';
comment on column CASHFLOW.qmxjye
  is '��ĩ�ֽ��ֽ�ȼ������
';
-- Create/Recreate primary, unique and foreign key constraints 
alter table CASHFLOW
  add primary key (ID);
-- Create/Recreate indexes 
create unique index INDEX_CASHFLOW on CASHFLOW (CODE, RELEASE_DATE);
