create table qglw_job_timer(
MSGNO varchar(6),
name varchar(50),
group_name varchar(50),
start_time varchar(14),
end_time varchar(14),
cron varchar(30),
job_status varchar(20),
plan_status varchar(20),
is_concurrent varchar(4),
method_name varchar(50),
bean_name varchar(100),
description varchar(1000),
create_user_id varchar(32),
create_date varchar(14),
modify_user_id varchar(32),
modify_date varchar(14)
)

insert into qglw_job_timer
(MSGNO,name,group_name,start_time,end_time,cron,job_status,plan_status,
        is_concurrent,method_name,bean_name,description)
        values
        ('1504','testname','testgroup','20181127105200','20181227105200',
        '*/10 * * * * ?','1','1','1','sendMsg4SOA','com.ylzinfo.app.service.InsProvSOAService',
        '参保省出院结算数据日对账')