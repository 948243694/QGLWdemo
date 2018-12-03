alter table qglw_kc51 add AAE314 varchar2(3);
comment on column qglw_kc51.aae314 is '对账标志，0-失败，1-成功，2-未对账'

alter table qglw_kc51 add AAA204 varchar2(200);
comment on column qglw_kc51.AAA204 is '提示信息'