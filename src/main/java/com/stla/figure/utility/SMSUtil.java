package com.stla.figure.utility;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.IOException;

/**
 * Created by Tibers on 16/6/2.
 * 互亿无线短信接口
 */
public class SMSUtil {

    private static final String USERNAME = "C54908558";//189账号下的
    private static final String PASSWORD = "b194ed05bde2a55c8dc8a3771cc27512";//189账号下的
//    private static final String USERNAME = "C31379133";//151账号下的
//    private static final String PASSWORD = "8cc478cc857bdc9910ca1811a042a0bd";//151账号下的
    private static final String REQUEST_URL = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";

    /**
     * @author:Shuoshi.Yan
     * @description: 发送短信
     * @date: 2019/11/20 10:07
     * @param: mobile 手机号，content 发送内容，signName 短信签名
     * @return:
     */
    private static Element send(String mobile, String content,String signName) throws RuntimeException {
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(REQUEST_URL);

        client.getParams().setContentCharset("UTF-8");
        method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=GBK");

        NameValuePair[] data = {//提交短信
                new NameValuePair("account", USERNAME), //查看用户名是登录用户中心->验证码短信->产品总览->APIID
                new NameValuePair("password", PASSWORD),  //查看密码请登录用户中心->验证码短信->产品总览->APIKEY
                new NameValuePair("mobile", mobile),
                new NameValuePair("content", content),
                new NameValuePair("sign", signName),

        };
        method.setRequestBody(data);
        Element root = null;
        try {
            client.executeMethod(method);
            String SubmitResult =method.getResponseBodyAsString();

            Document doc = DocumentHelper.parseText(SubmitResult);
            root = doc.getRootElement();

            String code = root.elementText("code");
            String msg = root.elementText("msg");
            String smsid = root.elementText("smsid");

            System.out.println(code);
            System.out.println(msg);
            System.out.println(smsid);

            if("2".equals(code)){
                System.out.println("短信提交成功");
            }

        } catch (HttpException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            // Release connection
            method.releaseConnection();
            client.getHttpConnectionManager().closeIdleConnections(0);
        }
        return root;

    }

    public static Element sendAuthCode(String mobile, String content,String signName) throws RuntimeException {
        return send(mobile, content,signName);
    }

    public static void main(String[] args) {
        SMSUtil.sendAuthCode("16619817752", "我在這裡為master服務，您负责小哆虚拟人产品有客户人咨询啦。请您通过后台及时查看哟~","小哆智能");
    }

}
