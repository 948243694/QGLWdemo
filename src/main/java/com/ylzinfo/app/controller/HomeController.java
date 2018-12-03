package com.ylzinfo.app.controller;

import com.ylzinfo.app.dao.Qglw_kc26_zyDao;
import com.ylzinfo.app.dao.STimetaskDao;
import com.ylzinfo.app.service.HospProvSOAService;
import com.ylzinfo.app.service.InsProvSOAService;
import com.ylzinfo.app.service.LogService;
import com.ylzinfo.app.utils.ApplicationContextUtil;
import com.ylzinfo.app.utils.XmlSerializerUtil;
import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/home")
public class HomeController extends BaseController{

    private static final Logger logger = Logger.getLogger(HomeController.class);

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    private InsProvSOAService insProvSOAService;

    @RequestMapping("/charges")
    @ResponseBody
    public String serviceTest(){
        insProvSOAService.getChargesList();
        return "test";
    }

    @RequestMapping("/demo")
    @ResponseBody
    public String Test() {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "test";

    }
}
