package com.stla.figure.controller;

import com.github.pagehelper.PageInfo;
import com.stla.figure.bean.*;
import com.stla.figure.deploy.ApiResult;
import com.stla.figure.deploy.AuthToken;
import com.stla.figure.server.WebXdResourceServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

//小哆webResource资源
@RestController
@RequestMapping("/xdresource")
public class WebXdResourceController {


    @Autowired
    WebXdResourceServer webXdResourceServer;

    //前端请求人物id后查询对应人物情绪动作音色
    //情绪,动作,音色 表情资源库(含表情动作标识码,以及表情时长需要刘浪配合数据提供)
    @AuthToken
    @RequestMapping("/resourceBean")
    public ApiResult resourceBean(HttpServletRequest request, @RequestBody xdAiResource bean){
        HashMap<String, List> outBean = webXdResourceServer.resourceBean(bean);
        return  ApiResult.success(outBean);
    }

    //主播形象列表
    @AuthToken
    @RequestMapping("/aiList")
    public ApiResult aiList(HttpServletRequest request, @RequestBody xdAi bean){
        List<xdAi> outBean =  webXdResourceServer.aiList();
        return  ApiResult.success(outBean);
    }

    //用户资源列表
    @AuthToken
    @RequestMapping("/settingList")
    public ApiResult settingList(HttpServletRequest request, @RequestBody xdResourceSetting bean){
        bean.setUserId((Integer) request.getAttribute("USER_KEY"));
        PageInfo<xdResourceSetting> outBean = webXdResourceServer.settingList(bean);
        return  ApiResult.success(outBean);
    }

    //用户资源列表(选中)
    @AuthToken
    @RequestMapping("/settingListOpt")
    public ApiResult settingListOpt(HttpServletRequest request, @RequestBody xdResourceSetting bean){
        bean.setUserId((Integer) request.getAttribute("USER_KEY"));
        List<xdResourceSetting> outBean = webXdResourceServer.settingListOpt(bean);
        return  ApiResult.success(outBean);
    }

    //用户资源添加
    @AuthToken
    @RequestMapping("/settingAdd")
    public ApiResult settingAdd(HttpServletRequest request, @RequestBody xdResourceSetting bean){
        int isBean = webXdResourceServer.settingAdd(bean);
        return  ApiResult.success(isBean);
    }

    //用户资源删除
    @AuthToken
    @RequestMapping("/settingDel")
    public ApiResult settingDel(HttpServletRequest request, @RequestBody xdResourceSetting bean){
        int isBean = webXdResourceServer.settingDel(bean);
        return  ApiResult.success(isBean);
    }

    //用户资源大小存量
    @AuthToken
    @RequestMapping("/settingSum")
    public ApiResult settingSum(HttpServletRequest request, @RequestBody xdResourceSetting bean){
        bean.setUserId((Integer) request.getAttribute("USER_KEY"));
        Double outBean = webXdResourceServer.settingSum(bean);
        HashMap<String, Double> out = new HashMap<>();
        out.put("settingSum",outBean);
        return  ApiResult.success(out);
    }


    //主播形象配置添加
    @AuthToken
    @RequestMapping("/aiAdd")
    public ApiResult aiAdd(HttpServletRequest request, @RequestBody xdAi bean){
        int isBean = webXdResourceServer.aiAdd(bean);
        return  ApiResult.success(null);
    }

    //主播形象配置编辑
    @AuthToken
    @RequestMapping("/aiUpd")
    public ApiResult aiUpd(HttpServletRequest request, @RequestBody xdAi bean){
        int isBean = webXdResourceServer.aiUpd(bean);
        return  ApiResult.success(null);
    }

    //主播形象配置删除
    @AuthToken
    @RequestMapping("/aiDel")
    public ApiResult aiDel(HttpServletRequest request, @RequestBody xdAi bean){
        int isBean = webXdResourceServer.aiDel(bean);
        return  ApiResult.success(null);
    }

    //资源添加
    @AuthToken
    @RequestMapping("/resourceAdd")
    public ApiResult resourceAdd(HttpServletRequest request, @RequestBody xdAiResource bean){
        xdAiResource outBean = webXdResourceServer.resourceAdd(bean);
        return  ApiResult.success(outBean);
    }

    //资源编辑
    @AuthToken
    @RequestMapping("/resourceUpd")
    public ApiResult resourceUpd(HttpServletRequest request, @RequestBody xdAiResource bean){
        xdAiResource outBean = webXdResourceServer.resourceUpd(bean);
        return  ApiResult.success(outBean);
    }

    //资源删除
    @AuthToken
    @RequestMapping("/resourceDel")
    public ApiResult resourceDel(HttpServletRequest request, @RequestBody xdAiResource bean){
        int isBean = webXdResourceServer.resourceDel(bean);
        return  ApiResult.success(null);
    }

}
