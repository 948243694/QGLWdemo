create table qglw_job_clog(
AAZ496 VARCHAR2(30) not null, --原发送方报文ID
LOGID0 NUMBER(16) not null, --日志ID
AAE030 VARCHAR2(14) not null, --发送时间
AAE031 VARCHAR2(14) not null, --获取部平台信息时间
LogDate VARCHAR2(14) not null, --写入日志时间
MSGNO VARCHAR2(6) not null, --交易代码
AAE314 VARCHAR2(3) not null, --成功标志
AAA204 VARCHAR2(300), --提示信息
autosign varchar2(3),
AKC070 varchar2(500),
AKC071 varchar2(500)
)

