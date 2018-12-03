package com.ylzinfo.app.utils;

import com.ylzinfo.app.model.Qglw_ke84_bodyDTO;
import com.ylzinfo.wtc.client.Rstring;
import com.ylzinfo.wtc.client.RstringHome;
import weblogic.wtc.jatmi.TPException;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Title:Department services FML message utility class
 * Description:部服务FML报文工具类
 * Copyright：Copyright(c) 2018-2019
 * Company:易联众信息技术股份有限公司
 * @author :yongjun Zhang
 * @version :1.0
 */

public class FmlUtil {

    private static Qglw_ke84_bodyDTO qglw_ke84_bodyDTO= new Qglw_ke84_bodyDTO();
    private static final String QGLW_URL_PROVIDER="t3://10.96.20.20:8000";


    /**
     * 设置报文公共头信息
     * @return
     */
    public static boolean preFMLHeader(){
        qglw_ke84_bodyDTO.setN_VER("V1.0");
        qglw_ke84_bodyDTO.setN_AAB299("350000");
        qglw_ke84_bodyDTO.setN_AAF011("福建省");
        qglw_ke84_bodyDTO.setN_AAB301("100000");
        qglw_ke84_bodyDTO.setN_AAF018("100000");
        qglw_ke84_bodyDTO.setN_AKB020("");
        qglw_ke84_bodyDTO.setN_AAC999("");
        qglw_ke84_bodyDTO.setN_MSGNO("");
        qglw_ke84_bodyDTO.setN_MSGID("");
        qglw_ke84_bodyDTO.setN_ORGMSGNO("");
        qglw_ke84_bodyDTO.setN_ORGMSGID("");
        qglw_ke84_bodyDTO.setN_INPUT("");
        return true;
    }

    /**
     * 获取十位随机数
     * @return
     */
    private static String getRandom(){
        String random="";
        for (int i = 0; i < 10; i++) {
            random =  random + (int)(Math.random()*(10+1));
        }
        return random;
    }


    /**
     * 获取请求信息List集合
     * @return
     */
    public static List<String> getMsgList(){
        List<String> listData =new ArrayList<String>();
        if(null !=  qglw_ke84_bodyDTO){
            listData.add(qglw_ke84_bodyDTO.getN_VER());   //N_VER 0
            listData.add(qglw_ke84_bodyDTO.getN_AAB299());   //N_AAB299 1
            listData.add(qglw_ke84_bodyDTO.getN_AAF011());   //N_AAF011 2
            listData.add(qglw_ke84_bodyDTO.getN_AAB301());   //N_AAB301 3
            listData.add(qglw_ke84_bodyDTO.getN_AAF018());   //N_AAF018 4
            listData.add(qglw_ke84_bodyDTO.getN_AKB020());   //N_AKB020 5
            listData.add(qglw_ke84_bodyDTO.getN_AAC999());   //N_AAC999 6
            listData.add(qglw_ke84_bodyDTO.getN_MSGNO());   //N_MSGNO 7
            listData.add(qglw_ke84_bodyDTO.getN_MSGID());   //N_MSGID 8
            listData.add(qglw_ke84_bodyDTO.getN_ORGMSGNO());   //N_ORGMSGNO 9
            listData.add(qglw_ke84_bodyDTO.getN_ORGMSGID());   //N_ORGMSGID 10
            listData.add(qglw_ke84_bodyDTO.getN_INPUT());   //N_INPUT 11
            listData.add(qglw_ke84_bodyDTO.getN_WORKDATE());   //N_WORKDATE 12
            listData.add(qglw_ke84_bodyDTO.getN_CAINFO());   //N_CAINFO 13
            listData.add(qglw_ke84_bodyDTO.getN_REFMSGID());   //N_REFMSGID 14
            listData.add(qglw_ke84_bodyDTO.getN_OUTPUT());   //N_OUTPUT 15
            listData.add(qglw_ke84_bodyDTO.getN_STATUS());   //N_STATUS 16
            listData.add(qglw_ke84_bodyDTO.getN_ERRMSG());   //N_ERRMSG 17
            listData.add(qglw_ke84_bodyDTO.getTRANSLOGID());   //N_TRANSLOG 18
        }
        return listData;
    }

    public static String sendMsg4SOA(String input) throws TPException, RemoteException, CreateException, NamingException {
        FmlUtil.preFMLHeader();
        Date now=new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String time=dateFormat.format(now);
        qglw_ke84_bodyDTO.setN_MSGNO("1504");
        qglw_ke84_bodyDTO.setN_MSGID(qglw_ke84_bodyDTO.getN_AAB299()+time+getRandom());
        qglw_ke84_bodyDTO.setN_INPUT(input);

        Properties properties = new Properties();
        properties.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
        properties.put(Context.PROVIDER_URL,QGLW_URL_PROVIDER );
        System.out.println("WTC前置机URL:"+QGLW_URL_PROVIDER);
        Context context= new InitialContext(properties);
        Object ref = context.lookup("tuxedo.services.RstringHome"); //通过ejb的JNDI name查找到EJBHome对象tuxedo.services.SISERVICEHome
        RstringHome rh = (RstringHome) PortableRemoteObject.narrow(ref,RstringHome.class);//得到EJBHome
        Rstring rb =  rh.create();
        List list  = rb.Rstring(FmlUtil.getMsgList());
        convetList2Qglw_ke84DTO(list);
        return qglw_ke84_bodyDTO.getN_OUTPUT();
    }

    private static boolean convetList2Qglw_ke84DTO(List list) {
        qglw_ke84_bodyDTO.setN_AAB299((String)list.get(1));//发送发行政区划
        qglw_ke84_bodyDTO.setN_AAB301((String)list.get(3));//接收方行政区号
        qglw_ke84_bodyDTO.setN_AKB020((String)list.get(5));//定点医疗机构编码
        qglw_ke84_bodyDTO.setN_AAC999((String)list.get(6));//个人管理码
        qglw_ke84_bodyDTO.setN_WORKDATE((String)list.get(13));//交易时间
        qglw_ke84_bodyDTO.setN_REFMSGID((String)list.get(14));//接收方报文ID
        qglw_ke84_bodyDTO.setN_OUTPUT((String)list.get(15));//返回报文
        qglw_ke84_bodyDTO.setN_STATUS((String)list.get(16));//报文状态
        qglw_ke84_bodyDTO.setN_ERRMSG((String)list.get(17));//错误信息
        qglw_ke84_bodyDTO.setTRANSLOGID((String)list.get(18));//日志信息
        return true;
    }


}
