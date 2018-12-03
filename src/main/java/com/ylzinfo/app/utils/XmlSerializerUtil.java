package com.ylzinfo.app.utils;

import com.ylzinfo.app.model.Qglw_kc26_zyDTO;
import com.ylzinfo.app.model.Qglw_kc51_bodyDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Title:FML message serialization tool class
 * Description:FML报文序列化工具类
 * Copyright：Copyright(c) 2018-2019
 * Company:易联众信息技术股份有限公司
 * @author :yongjun Zhang
 * @version :1.0
 */

public class XmlSerializerUtil {

    private final static Log logger = LogFactory.getLog(XmlSerializerUtil.class);

    /**
     * 获取Qglw_kc26_zy的实体对象所转换成的XML字符串
     * @param qglw_kc26_zyDTOS  Gglw_kc26_zy实体对象集合
     * @param data 一级节点名
     * @param inputs 二级节点名
     * @param input 三级节点名
     * @return  XML字符串
     */
    public static String Qglw_kc26_zyGenXmlStr(ArrayList<Qglw_kc26_zyDTO> qglw_kc26_zyDTOS, String data, String inputs, String input){

        Document doc = DocumentHelper.createDocument();
        Element dataEle = doc.addElement(data);
        Element inputsEle = dataEle.addElement(inputs);
        HashMap<String,Object> propertyInfo = null;
        for(Qglw_kc26_zyDTO qglw_kc26_zyDTO:qglw_kc26_zyDTOS){
            Element inputEle = inputsEle.addElement(input);
            propertyInfo = XmlSerializerUtil.getAllProperty(qglw_kc26_zyDTO);
            for(Map.Entry<String, Object> property: propertyInfo.entrySet()){
                if(!property.getKey().equals("AKB067")) {      //个人现金支付信息无需发送部平台
                    Element propertyEle = inputEle.addElement(property.getKey());
                    propertyEle.setText(property.getValue().toString());
                }
            }
        }
        OutputFormat format = OutputFormat.createCompactFormat();
        format.setEncoding("utf-8");
        StringWriter writer = new StringWriter();
        XMLWriter output = new XMLWriter(writer, format);
        try {
            output.write(doc);
        } catch (IOException e) {
            logger.error("XMLWriter writer error");
        }finally{
            try {
                writer.close();
                output.close();
            } catch (IOException ex) {
                logger.error("XMLWriter close error");
            }
        }
        return writer.toString();
    }


    /**
     * 根据属性名获取该对象对应属性的值
     * @param fieldName 属性名
     * @param obj 对象
     * @return 该对象该属性的值
     */
    private static Object getFieldValueByName(String fieldName, Object obj) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = obj.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(obj, new Object[] {});
            return value;
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 获取该对象所有属性的名称与值
     * @param obj 对象
     * @return 所有属性的名称与值为键值对的HashMap集合
     */
    private static HashMap<String,Object> getAllProperty(Object obj){
        HashMap<String,Object> properties = new HashMap<String, Object>();
        Class<?> clazz = obj.getClass();
        for(Field field : clazz.getDeclaredFields()) {
            String propertyName = field.getName();
            properties.put(propertyName, getFieldValueByName(propertyName,obj));
        }
        return properties;
    }


    /**
     * 通过对账信息查找对应的出院消费记录
     * @param qglw_kc51_bodyDTO 对账信息
     * @param qglwKc26ZyDTOS 出院消费记录集合
     * @return  单条出院消费记录
     */
    private static Qglw_kc26_zyDTO searchRI(@NotNull Qglw_kc51_bodyDTO qglw_kc51_bodyDTO, ArrayList<Qglw_kc26_zyDTO> qglwKc26ZyDTOS){
        Qglw_kc26_zyDTO info = new Qglw_kc26_zyDTO();
        String AAB299 = qglw_kc51_bodyDTO.getAAB299();
        String AAB301 = qglw_kc51_bodyDTO.getAAB301();
        String AKC190 = qglw_kc51_bodyDTO.getAKC190();
        String AAZ216 = qglw_kc51_bodyDTO.getAAZ216();
        for(Iterator charges = qglwKc26ZyDTOS.iterator(); charges.hasNext();) {
            info = (Qglw_kc26_zyDTO) charges.next();
            if(AAB299.equals(info.getAAB299()) && AAB301.equals(info.getAAB301())
                    && AKC190.equals(info.getAKC190()) && AAZ216.equals(info.getAAZ216())) {
                charges.remove();
                return info;
            }
        }
        return info;
    }


    /**
     * 根据XML字符串获取Qglw_kc51对象集合
     * @param outputInfo xml字符串
     * @param qglwKc26ZyDTOS 对应输入信息
     * @return
     * @throws DocumentException
     */
    public static ArrayList<Qglw_kc51_bodyDTO> xmlGenQglw_kc51Bean(String outputInfo,ArrayList<Qglw_kc26_zyDTO> qglwKc26ZyDTOS) throws DocumentException {
        ArrayList<Qglw_kc51_bodyDTO> qglw_kc51_bodyDTOs = new ArrayList<Qglw_kc51_bodyDTO>();
        Qglw_kc26_zyDTO info = null;
        Document document = DocumentHelper.parseText(outputInfo);
        Element dataEle = document.getRootElement();
        Element outputsEle = dataEle.element("OUTPUTS");
        Iterator outputs = outputsEle.elementIterator("OUTPUT");
        while(outputs.hasNext()){
            Qglw_kc51_bodyDTO qglw_kc51_bodyDTO = new Qglw_kc51_bodyDTO();
            Element outputEle = (Element) outputs.next();
            qglw_kc51_bodyDTO.setAAE314(outputEle.elementTextTrim("AAE314"));
            qglw_kc51_bodyDTO.setAAA204(outputEle.elementTextTrim("AAA204"));
            qglw_kc51_bodyDTO.setAAB299(outputEle.elementTextTrim("AAB299"));
            qglw_kc51_bodyDTO.setAAB301(outputEle.elementTextTrim("AAB301"));
            qglw_kc51_bodyDTO.setAKC190(outputEle.elementTextTrim("AKC190"));
            qglw_kc51_bodyDTO.setAAZ216(outputEle.elementTextTrim("AAZ216"));
            info = XmlSerializerUtil.searchRI(qglw_kc51_bodyDTO,qglwKc26ZyDTOS);
            qglw_kc51_bodyDTO.setAKC264(info.getAKC264());
            qglw_kc51_bodyDTO.setAKE149(info.getAKE149());
            qglw_kc51_bodyDTO.setAKC194(info.getAKC194());
            qglw_kc51_bodyDTO.setAAZ497(info.getAAZ497());
            qglw_kc51_bodyDTO.setAAZ498(info.getAAZ498());
            qglw_kc51_bodyDTO.setAAA113(info.getAAA113());
            qglw_kc51_bodyDTO.setAKB067(info.getAKB067());
            qglw_kc51_bodyDTOs.add(qglw_kc51_bodyDTO);
        }
        return qglw_kc51_bodyDTOs;
    }


}
