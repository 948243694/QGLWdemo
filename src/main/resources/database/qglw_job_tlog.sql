create table qglw_job_tlog(
log_id varchar2(32) primary key,
create_date varchar2(14),
MSGNO varchar2(6),
reason varchar(100),
state varchar(10),
message varchar(100)
)