package com.ylzinfo.app.service;

import com.ylzinfo.app.dao.STimetaskLogDao;
import com.ylzinfo.app.model.STimetaskLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Title :Log management services
 * Description :日志管理服务
 * CopyrightCopyright(c) 2018-2019
 * Company :易联众信息技术股份有限公司
 * @author :yongjun Zhang
 * @version :1.0
 */
@Component
public class LogService extends AbstractService {

    @Autowired
    public STimetaskLogDao sTimetaskLogDao;

    /**
     * 定时任务日志数据落地
     * @param sTimetaskLog
     */
    @Transactional
    public void writeTimerRecord(STimetaskLog sTimetaskLog){
        sTimetaskLogDao.insertTimeTaskLogTA(sTimetaskLog);
    }

    public void getTimerRecord() {sTimetaskLogDao.getAllLoglListTA();}
}
