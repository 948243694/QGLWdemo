package com.ylzinfo.app.service;

import com.ylzinfo.app.dao.Qglw_kc26_zyDao;
import com.ylzinfo.app.dao.Qglw_kc51Dao;
import com.ylzinfo.app.model.Qglw_kc26_zyDTO;
import com.ylzinfo.app.model.Qglw_kc51_bodyDTO;
import com.ylzinfo.app.utils.FmlUtil;
import com.ylzinfo.app.utils.XmlSerializerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;


/**
 * Title:Reconciliation of the insured Province
 * Description:参保省出院结算日对账
 * Copyright：Copyright(c) 2018-2019
 * Company:易联众信息技术股份有限公司
 * @author :yongjun Zhang
 * @version :1.0
 */

@Component
public class InsProvSOAService extends AbstractService {

    private static ArrayList<Qglw_kc26_zyDTO> chargesList;
    private static ArrayList<Qglw_kc51_bodyDTO> soaInfo;
    private String inputStr;
    private String outputStr;

    @Autowired
    private Qglw_kc26_zyDao qglwkc26Dao;

    @Autowired
    private Qglw_kc51Dao qglw_kc51Dao;


    /**
     * 获取参保地机构的出院消费记录列表
     * @return
     */
    @Transactional
    public boolean getChargesList() {
        chargesList = qglwkc26Dao.getHospChargesTA();
        return true;
    }

    /**
     * 发送参保地出院消费记录数据序列化后的xml字符串至部平台
     * 接收部平台返回的每日对账数据（soaInfo）
     * 并数据落地
     * @return
     */
    public boolean sendMsg4SOA(){
        this.getChargesList();
        inputStr = XmlSerializerUtil.Qglw_kc26_zyGenXmlStr(chargesList,"DATA","INPUTS","INPUT");
        try {
            outputStr = FmlUtil.sendMsg4SOA(inputStr);
            soaInfo = XmlSerializerUtil.xmlGenQglw_kc51Bean(outputStr,chargesList);
            qglw_kc51Dao.batchAddSOAInfoTA(soaInfo);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (CreateException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("this is a timer test");
        return true;

    }
}
