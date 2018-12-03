package com.ylzinfo.app.controller;

import com.ylzinfo.app.model.STimetask;
import com.ylzinfo.app.model.STimetaskLog;
import com.ylzinfo.app.service.LogService;
import com.ylzinfo.app.service.TimingTaskService;
import com.ylzinfo.app.utils.ApplicationContextUtil;
import com.ylzinfo.app.utils.UuidUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/test")
public class TestController {

    private static final Logger logger = Logger.getLogger(com.ylzinfo.app.controller.HomeController.class);

    @Autowired
    private TimingTaskService timingTaskService;

    @RequestMapping("/timing")
    @ResponseBody
    public String serviceTest() {
        STimetask task1 = new STimetask();
        task1.setMSGNO("25555");
        task1.setGroupName("testgroup"); // 任务组
        task1.setStartTime("20181129134506");
        task1.setEndTime("20191129134506");
        task1.setPlanStatus("1");
        task1.setName("test3");// 任务名称
        task1.setJobStatus("1"); // 任务发布状态
        task1.setIsConcurrent("1"); // 运行状态
        task1.setCron("*/10 * * * * ?");
        task1.setBeanName("com.ylzinfo.app.test.TimerTest");// 一个以所给名字注册的bean的实例
        task1.setMethodName("execuTest3");
        task1.setDescription("测试函数3，add测试");
        timingTaskService.addTimingTask(task1);
        return "successs";
    }

    @RequestMapping("/log")
    @ResponseBody
    public String Logtest(){
        STimetaskLog tlog = new STimetaskLog();
        LogService logService = (LogService) ApplicationContextUtil.getApplicationContext().getBean(LogService.class);
        tlog.setLogID(UuidUtil.getUUID());
        tlog.setCreateDate(new Date());
        tlog.setMSGNO("11203");
        tlog.setReason("");
        tlog.setMessage("test");
        tlog.setState("fail");
        logService.writeTimerRecord(tlog);
        return "test";
    }

    @RequestMapping("/change")
    @ResponseBody
    public String ChangeTest() {
        STimetask task1 = new STimetask();
        task1.setMSGNO("25534");
        task1.setGroupName("testgroup"); // 任务组
        task1.setStartTime("20181129134506");
        task1.setEndTime("20191129134506");
        task1.setPlanStatus("1");
        task1.setName("test2");// 任务名称
        task1.setJobStatus("1"); // 任务发布状态
        task1.setIsConcurrent("1"); // 运行状态
        task1.setCron("*/15 * * * * ?");
        task1.setBeanName("com.ylzinfo.app.test.TimerTest");// 一个以所给名字注册的bean的实例
        task1.setMethodName("execuTest2");
        task1.setDescription("测试函数2，add测试");
        timingTaskService.resetTimerByName(task1);
        return "change";
    }

    @RequestMapping("/stop")
    @ResponseBody
    public String StopTest() {
        timingTaskService.stopTimingTask("test2","testgroup");
        return "stop";
    }

    @RequestMapping("/restart")
    @ResponseBody
    public String restartTest() {
        timingTaskService.restartTimingTask("test2","testgroup");
        return "restart";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String deleteTest() {
        timingTaskService.deleteTaskByName("test2","testgroup");
        return "delete";
    }



}
