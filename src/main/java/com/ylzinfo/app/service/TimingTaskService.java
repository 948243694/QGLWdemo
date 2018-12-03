package com.ylzinfo.app.service;

import com.ylzinfo.app.dao.STimetaskDao;
import com.ylzinfo.app.model.STimetask;
import com.ylzinfo.app.model.ScheduleJob;
import com.ylzinfo.app.utils.timer.InitQuartzJob;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;


/**
 * Title :Timed task management
 * Description :定时任务管理
 * CopyrightCopyright(c) 2018-2019
 * Company :易联众信息技术股份有限公司
 * @author :yongjun Zhang
 * @version :1.0
 */
@Component
public class TimingTaskService extends AbstractService {

    @Autowired
    private STimetaskDao sTimetaskDao;

    /**
     * 添加一个定时任务到调度器中
     * @param sTimetask
     */
    public void addTimingTask(STimetask sTimetask){
        sTimetaskDao.addTimeTaskTA(sTimetask);
        ScheduleJob scheduleJob = new ScheduleJob(sTimetask);
        try {
            InitQuartzJob.addJob(scheduleJob);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    /**
     * 暂停一个调度器中到定时任务
     * @param jobName
     * @param groupName
     */
    public void stopTimingTask(String jobName,String groupName){
        try {
            InitQuartzJob.stop(jobName,groupName);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    /**
     * 重启调度器中已暂停的定时任务
     * @param jobName
     * @param groupName
     */
    public void restartTimingTask(String jobName,String groupName){
        try {
            InitQuartzJob.restart(jobName,groupName);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取所有定时任务列表
     * @return
     */
    public ArrayList<STimetask> getAllTmingTask(){
        return sTimetaskDao.getTimeTaskListTA();
    }

    /**
     * 获取可执行的定时任务列表
     * @return
     */
    public ArrayList<STimetask> getExecutableTask(){
        return sTimetaskDao.getExecutableTaskTA();
    }

    /**
     * 删除定时任务并从调度器中移除
     * @param jobName
     * @param groupName
     */
    public void deleteTaskByName(String jobName,String groupName){
        sTimetaskDao.deleteTaskByNameTA(jobName,groupName);
        try {
            InitQuartzJob.delete(jobName,groupName);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }


    }

    /**
     * 修改定时任务信息
     * @param sTimetask
     */
    public void resetTimerByName(STimetask sTimetask){
        sTimetaskDao.updateByNameTA(sTimetask);
        ScheduleJob scheduleJob = new ScheduleJob(sTimetask);
        try {
            InitQuartzJob.addJob(scheduleJob);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

}
