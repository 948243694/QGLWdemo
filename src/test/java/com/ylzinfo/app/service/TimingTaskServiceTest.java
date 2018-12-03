package com.ylzinfo.app.service;

import com.ylzinfo.app.model.STimetask;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml"})
public class TimingTaskServiceTest {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(com.ylzinfo.app.controller.HomeController.class);

    @Autowired
    private TimingTaskService timingTaskService;

    @Test
    public void testAdd() {
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
    }

    @Test
    public void testUpdate() {
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
    }

    @Test
    public void testStop() {
        timingTaskService.stopTimingTask("test2","testgroup");
    }

    @Test
    public void testRestart() {
        timingTaskService.restartTimingTask("test2","testgroup");
    }

    @Test
    public void testDelete() {
        timingTaskService.deleteTaskByName("test2","testgroup");
    }



}
