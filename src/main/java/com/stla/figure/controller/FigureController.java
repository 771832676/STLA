package com.stla.figure.controller;

import com.github.pagehelper.PageInfo;
import com.stla.figure.bean.authorization;
import com.stla.figure.bean.authorizationApi;
import com.stla.figure.bean.pageBean;
import com.stla.figure.bean.redisBean;
import com.stla.figure.deploy.ApiResult;
import com.stla.figure.server.FigureServer;
import com.stla.figure.utility.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FigureController {


    @Autowired
    FigureServer FigureServer;

    @RequestMapping("/setRedis")
    public void setRedis(@RequestBody redisBean bean){
        FigureServer.setRedis(bean);
    }

    //录入申请信息得到静态密钥
    @RequestMapping("/accredit")
    public ApiResult Accredit(@RequestBody authorization bean){
        bean.setIsDelete(0);
        bean.setIsOfficial(0);
        bean.setCreateTime(new Date());
        //boolean b = ObjectUtils.allFieldIsNULL(bean);
        String token = FigureServer.Accredit(bean);
        return  ApiResult.success(bean);
    }

    //使用令牌获得24h认证Code码
    @RequestMapping("/getCode")
    public ApiResult getCode(@RequestBody authorizationApi bean){
        ApiResult getCode = FigureServer.getCode(bean);
        return  getCode;
    }

    //验证认证code码是否在有效期(访问量随授权增大-后期考虑支持多线程)
    @RequestMapping("/isExist")
    public ApiResult isExist(@RequestBody authorizationApi bean){
        boolean isExist = FigureServer.isExist(bean);
        HashMap<String, String> map = new HashMap<>();
        map.put("isExist",""+isExist);//24h令牌码
        return  ApiResult.success(map);
    }

    //授权列表分页
    @RequestMapping("/accreditList")
    public ApiResult accreditList(@RequestBody authorization bean){
        PageInfo<authorization> pageList = FigureServer.getList(bean);
        return  ApiResult.success(pageList);
    }

    //授权详情
    @RequestMapping("/accreditDetails")
    public ApiResult accreditDetails(@RequestBody authorization bean){
        authorization accreditDetails = FigureServer.accreditDetails(bean.getId());
        return  ApiResult.success(accreditDetails);
    }

    //授权修改接口
    @RequestMapping("/accreditUpdate")
    public ApiResult accreditUpdate(@RequestBody authorization bean){
        bean.setUpdateTime(new Date());
        FigureServer.accreditUpdate(bean);
        return  ApiResult.success(bean);
    }

    //授权删除接口
    @RequestMapping("/accreditDelete")
    public ApiResult accreditDelete(@RequestBody authorization bean){
        bean.setUpdateTime(new Date());
        FigureServer.accreditDelete(bean.getId());
        return  ApiResult.success(bean);
    }

    //获取正式版静态密钥-前端提供主键,结束时间
    @RequestMapping("/updAccredit")
    public ApiResult updAccredit(@RequestBody authorization bean){
        String token = FigureServer.updAccredit(bean);
        HashMap<String, String> map = new HashMap<>();
        map.put("apiToken",token);
        return  ApiResult.success(token);
    }

    //清除机器码
    @RequestMapping("/updDeviceCode")
    public ApiResult updDeviceCode(@RequestBody authorization bean){
        FigureServer.updDeviceCode(bean);
        return  ApiResult.success(bean);
    }


}
