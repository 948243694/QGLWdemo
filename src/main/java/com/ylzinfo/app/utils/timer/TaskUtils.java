package com.ylzinfo.app.utils.timer;

import com.ylzinfo.app.dao.STimetaskLogDao;
import com.ylzinfo.app.model.STimetaskLog;
import com.ylzinfo.app.model.ScheduleJob;
import com.ylzinfo.app.model.ValueObject;
import com.ylzinfo.app.service.LogService;
import com.ylzinfo.app.utils.ApplicationContextUtil;
import com.ylzinfo.app.utils.UuidUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Transient;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

public class TaskUtils {

    public final static Logger log = Logger.getLogger(TaskUtils.class);

    /**
     * 通过反射调用scheduleJob中定义的方法
     *
     * @param scheduleJob
     */
    @SuppressWarnings("unchecked")
    public static void invokMethod(ScheduleJob scheduleJob) {
        Object object = null;
        Class clazz = null;
        boolean flag = true;
        //if (StringUtils.isNotBlank(scheduleJob.getBeanClass())) { 不知道为何会运行isNotBlank函数时会自动结束。
        if (!"".equals(scheduleJob.getBeanClass()) && scheduleJob.getBeanClass()!=null ) {
            try {
                clazz = Class.forName(scheduleJob.getBeanClass());
                object = clazz.newInstance();
            } catch (Exception e) {
                flag = false;
                LogService logService = (LogService) ApplicationContextUtil.getApplicationContext().getBean(LogService.class);
                STimetaskLog tlog = new STimetaskLog();
                tlog.setLogID(UuidUtil.getUUID());
                tlog.setCreateDate(new Date());
                tlog.setMSGNO(scheduleJob.getJobId());
                tlog.setReason("未找到"+scheduleJob.getBeanClass()+"对应的class");
                tlog.setState("fail");
                logService.writeTimerRecord(tlog);
                e.printStackTrace();
            }
        }
        if (object == null) {
            flag = false;
            log.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，请检查是否配置正确！！！");
            flag = false;
            LogService logService = (LogService) ApplicationContextUtil.getApplicationContext().getBean(LogService.class);
            STimetaskLog tlog = new STimetaskLog();
            tlog.setLogID(UuidUtil.getUUID());
            tlog.setCreateDate(new Date());
            tlog.setMSGNO(scheduleJob.getJobId());
            tlog.setReason("未找到"+scheduleJob.getBeanClass()+"对应的class");
            tlog.setState("fail");
            logService.writeTimerRecord(tlog);
            return;
        }
        clazz = object.getClass();
        Method method = null;
        try {
            method = clazz.getDeclaredMethod(scheduleJob.getMethodName(), null);
        } catch (NoSuchMethodException e) {
            flag = false;
            flag = false;
            log.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，方法名设置错误！！！");
            flag = false;
            LogService logService = (LogService) ApplicationContextUtil.getApplicationContext().getBean(LogService.class);
            STimetaskLog tlog = new STimetaskLog();
            tlog.setLogID(UuidUtil.getUUID());
            tlog.setCreateDate(new Date());
            tlog.setMSGNO(scheduleJob.getJobId());
            tlog.setReason("未找到"+scheduleJob.getBeanClass()+"类下"+scheduleJob.getMethodName()+"对应的方法");
            tlog.setState("fail");
            logService.writeTimerRecord(tlog);
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (method != null) {
            try {
                method.invoke(object,null);
            } catch (IllegalAccessException e) {
                flag = false;
                LogService logService = (LogService) ApplicationContextUtil.getApplicationContext().getBean(LogService.class);
                STimetaskLog tlog = new STimetaskLog();
                tlog.setLogID(UuidUtil.getUUID());
                tlog.setCreateDate(new Date());
                tlog.setMSGNO(scheduleJob.getJobId());
                tlog.setReason("未找到"+scheduleJob.getBeanClass()+"类下"+scheduleJob.getMethodName()+"对应的方法参数设置错误");
                tlog.setState("fail");
                logService.writeTimerRecord(tlog);
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                flag = false;
                LogService logService = (LogService) ApplicationContextUtil.getApplicationContext().getBean(LogService.class);
                STimetaskLog tlog = new STimetaskLog();
                tlog.setLogID(UuidUtil.getUUID());
                tlog.setCreateDate(new Date());
                tlog.setMSGNO(scheduleJob.getJobId());
                tlog.setReason("未找到"+scheduleJob.getBeanClass()+"类下"+scheduleJob.getMethodName()+"对应的方法参数设置错误");
                tlog.setState("fail");
                logService.writeTimerRecord(tlog);
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                flag = false;
                LogService logService = (LogService) ApplicationContextUtil.getApplicationContext().getBean(LogService.class);
                STimetaskLog tlog = new STimetaskLog();
                tlog.setLogID(UuidUtil.getUUID());
                tlog.setCreateDate(new Date());
                tlog.setMSGNO(scheduleJob.getJobId());
                tlog.setReason("未找到"+scheduleJob.getBeanClass()+"类下"+scheduleJob.getMethodName()+"对应的方法参数设置错误");
                tlog.setState("fail");
                logService.writeTimerRecord(tlog);
                e.printStackTrace();
            }
        }
        if(flag){
            System.out.println("任务名称 = [" + scheduleJob.getJobName() + "]----------启动成功");
            STimetaskLog tlog = new STimetaskLog();
            LogService logService = (LogService) ApplicationContextUtil.getApplicationContext().getBean(LogService.class);
            tlog.setLogID(UuidUtil.getUUID());
            tlog.setCreateDate(new Date());
            tlog.setMSGNO(scheduleJob.getJobId());
            tlog.setReason("");
            tlog.setMessage("test");
            tlog.setState("success");
            logService.writeTimerRecord(tlog);

        }

    }
}
