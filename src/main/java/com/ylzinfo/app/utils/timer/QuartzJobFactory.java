package com.ylzinfo.app.utils.timer;

import com.ylzinfo.app.model.ScheduleJob;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class QuartzJobFactory implements Job {
    public final Logger log = Logger.getLogger(this.getClass());


    public void execute(JobExecutionContext context) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
        TaskUtils.invokMethod(scheduleJob);
    }

}
