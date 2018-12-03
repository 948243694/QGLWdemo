package com.ylzinfo.app.dao;

import com.ylzinfo.app.model.STimetask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml","classpath:mapper/STimetaskMapper.xml"})
public class STimetaskDaoTest {
    private Logger  log= LoggerFactory.getLogger(STimetaskDaoTest.class);

    @Autowired
    private STimetaskDao sTimetaskDao;

    @Test
    public void testAdd(){
       try {
           STimetask sTimetask = new STimetask();
           sTimetask.setMSGNO("1504");
           sTimetask.setName("addTest1");
           sTimetask.setGroupName("test");
           sTimetask.setJobStatus("1");
           sTimetask.setPlanStatus("1");
           sTimetask.setStartTime("20181130154002");
           sTimetask.setEndTime("20191130154002");
           sTimetask.setBeanName("com.ylzinfo.app.test.TimerTest");
           sTimetask.setMethodName("execuTest");
           sTimetask.setCron("*/10 * * * * ?");
           sTimetask.setDescription("测试函数1");
           sTimetask.setIsConcurrent("1");
           sTimetaskDao.addTimeTaskTA(sTimetask);
           log.info("======保存成功========");
       }catch (Exception e){
           log.info("======保存错误========");
       }

    }

    @Test
    public void testAddWithoutDescription(){
        try {
            STimetask sTimetask = new STimetask();
            sTimetask.setMSGNO("1504");
            sTimetask.setName("addWithoutDescription1");
            sTimetask.setGroupName("test");
            sTimetask.setJobStatus("1");
            sTimetask.setPlanStatus("1");
            sTimetask.setStartTime("20181130154002");
            sTimetask.setEndTime("20191130154002");
            sTimetask.setBeanName("com.ylzinfo.app.test.TimerTest");
            sTimetask.setMethodName("execuTest");
            sTimetask.setCron("*/10 * * * * ?");
            sTimetask.setIsConcurrent("1");
            sTimetaskDao.addTimeTaskTA(sTimetask);
            log.info("======保存成功========");
        }catch (Exception e){
            log.info("======保存错误========");
        }

    }

    @Test
    public void testdelete(){
        try {
            sTimetaskDao.deleteTaskByNameTA("addTest1","test");
            log.info("======保存成功========");
        }catch (Exception e){
            log.info("======保存错误========");
        }

    }


    @Test
    public void testupdate(){
        try {
            STimetask sTimetask = new STimetask();
            sTimetask.setMSGNO("1504");
            sTimetask.setName("addWithoutDescription1");
            sTimetask.setGroupName("test");
            sTimetask.setJobStatus("0");
            sTimetask.setPlanStatus("1");
            sTimetask.setStartTime("20181130154002");
            sTimetask.setEndTime("20191130154002");
            sTimetask.setBeanName("com.ylzinfo.app.test.TimerTest");
            sTimetask.setMethodName("execuTest3");
            sTimetask.setCron("*/10 * * * * ?");
            sTimetask.setIsConcurrent("1");
            sTimetaskDao.updateByNameTA(sTimetask);
            log.info("======保存成功========");
        }catch (Exception e){
            log.info("======保存错误========");
        }

    }


    @Test
    public void testSelect(){
        ArrayList<STimetask> list = null;
        try {
            list = sTimetaskDao.getTimeTaskListTA();
            System.out.println(list.size());
            log.info("======保存成功========");
        }catch (Exception e){
            log.info("======保存错误========");
        }

    }


}
