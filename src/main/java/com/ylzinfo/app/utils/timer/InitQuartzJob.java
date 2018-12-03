package com.ylzinfo.app.utils.timer;

import com.ylzinfo.app.dao.STimetaskDao;
import com.ylzinfo.app.dao.STimetaskLogDao;
import com.ylzinfo.app.model.STimetask;
import com.ylzinfo.app.model.STimetaskLog;
import com.ylzinfo.app.model.ScheduleJob;
import com.ylzinfo.app.service.TimingTaskService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.ArrayList;
import java.util.List;

public class InitQuartzJob implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(InitQuartzJob.class);

    private static ApplicationContext appCtx;

    public static SchedulerFactoryBean schedulerFactoryBean = null;

    public static TimingTaskService timingTaskService = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (this.appCtx == null) {
            this.appCtx = applicationContext;
        }
    }

    public static void init() {
        schedulerFactoryBean = (SchedulerFactoryBean) appCtx.getBean(SchedulerFactoryBean.class);
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            logger.info(scheduler.getSchedulerName());
        } catch (SchedulerException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        timingTaskService = (TimingTaskService) appCtx.getBean(TimingTaskService.class);
        List<STimetask> list = timingTaskService.getAllTmingTask();
        List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
        for (STimetask sTimetask : list) {
            ScheduleJob job1 = new ScheduleJob(sTimetask);
            jobList.add(job1);
        }
        for (ScheduleJob job : jobList) {
            try {
                addJob(job);
            } catch (SchedulerException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 添加任务
     *
     * @throws SchedulerException
     */
    public static void addJob(ScheduleJob job) throws SchedulerException {
        if (job == null || !ScheduleJob.STATUS_RUNNING.equals(job.getJobStatus())) {
            return;
        }
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        logger.debug(scheduler + "...........................................add");
        TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());

        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        // 不存在，创建一个
        if (null == trigger) {
            Class clazz = ScheduleJob.CONCURRENT_IS.equals(job.getIsConcurrent()) ? QuartzJobFactory.class
                    : QuartzJobFactoryDisallowConcurrentExecution.class;

            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup()).build();
            jobDetail.getJobDataMap().put("scheduleJob", job);

            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
            trigger = TriggerBuilder.newTrigger().withDescription(job.getJobId().toString()).withIdentity(job.getJobName(), job.getJobGroup())
                    .withSchedule(scheduleBuilder).build();

            scheduler.scheduleJob(jobDetail, trigger);
        } else {
            // Trigger已存在，那么更新相应的定时设置
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        }
    }

    public static void stop(String jobName,String groupName) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(jobName,groupName);
        scheduler.pauseJob(jobKey);
    }

    public static void restart(String jobName,String groupName) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(jobName,groupName);
        scheduler.resumeJob(jobKey);
    }

    public static void delete(String jobName,String groupName) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(jobName,groupName);
        scheduler.deleteJob(jobKey);
    }


}
