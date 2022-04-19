package com.stla.figure.controller;

import com.stla.figure.bean.xdAiLog;
import com.stla.figure.bean.xdAiResource;
import com.stla.figure.bean.xdUserAiBean;
import com.stla.figure.bean.xdWorkbenchBean;
import com.stla.figure.deploy.ApiResult;
import com.stla.figure.deploy.AuthToken;
import com.stla.figure.server.WebXdAiServer;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

//小哆人像视频编辑模块
@RestController
@RequestMapping("/xdAi")
public class WebXdAIController {

    @Autowired
    WebXdAiServer webXdAiServer;

    //添加作品
    @AuthToken
    @RequestMapping("/insetOpus")
    public ApiResult insetOpus(HttpServletRequest request, @RequestBody xdUserAiBean bean){
        bean.getXdUserAi().setUserId((Integer) request.getAttribute("USER_KEY"));
        ApiResult outBean = webXdAiServer.insetOpus(bean);
        return  ApiResult.success(outBean);
    }


    //日志
    @RequestMapping("/loge")
    public  List<xdAiLog> loge(HttpServletRequest request){
        List<xdAiLog> outBean = webXdAiServer.loge();
        return  outBean;
    }

}
