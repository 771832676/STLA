package com.stla.figure.controller;

import com.github.pagehelper.PageInfo;
import com.stla.figure.bean.*;
import com.stla.figure.deploy.ApiResult;
import com.stla.figure.deploy.AuthToken;
import com.stla.figure.server.WebxdUserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

//小哆webUser
@RestController
@RequestMapping("/xduser")
public class WebXdUserController {

    @Autowired
    WebxdUserServer  webxdUserServer;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${loginTimRedis}")
    private Integer loginTimRedis;

    //重置用户活跃度
    @AuthToken
    @RequestMapping("/active")
    public ApiResult active(HttpServletRequest request, @RequestBody xdUser bean){
        String message = (String) request.getAttribute("MESSAGE");
        String token = (String) request.getAttribute("TOKEN");
        redisTemplate.opsForValue().set(token, message, loginTimRedis, TimeUnit.SECONDS);
        return  ApiResult.success(null);
    }


    //用户信息
    @AuthToken
    @RequestMapping("/information")
    public ApiResult information(HttpServletRequest request, @RequestBody xdUser bean){
        xdUser outBean = webxdUserServer.information(bean);
        outBean.setPassword("");
        return  ApiResult.success(outBean);
    }

    //用户登录校验(redis)
    @AuthToken
    @RequestMapping("/loginVerify")
    public ApiResult loginVerify(HttpServletRequest request, @RequestBody xdUser bean){
        HashMap outBean = webxdUserServer.loginVerify(bean);
        return  ApiResult.success(outBean);
    }

    //用户登录
    @RequestMapping("/login")
    public ApiResult login(HttpServletRequest request, @RequestBody xdUser bean) throws Exception {
        HashMap outBean = webxdUserServer.login(bean);
        return  ApiResult.success(outBean);
    }

    //个人信息(统计)
    @AuthToken
    @RequestMapping("/statistics")
    public ApiResult statistics(HttpServletRequest request, @RequestBody xdUser bean){
        //Integer user_key = (Integer) request.getAttribute("USER_KEY");
        bean.setId((Integer) request.getAttribute("USER_KEY"));
        xdUserStatistics outBean = webxdUserServer.statistics(bean);
        return  ApiResult.success(outBean);
    }

    //用户草稿箱列表(分页)
    @AuthToken
    @RequestMapping("/draftDraftPageList")
    public ApiResult draftDraftPageList(HttpServletRequest request, @RequestBody xdUserAi bean){
        bean.setUserId((Integer) request.getAttribute("USER_KEY"));
        bean.setIsDraft(0);
        PageInfo<xdUserAi> outBean = webxdUserServer.draftPageList(bean);
        return  ApiResult.success(outBean);
    }

    //视频展示列表(分页)
    @AuthToken
    @RequestMapping("/draftPageList")
    public ApiResult draftPageList(HttpServletRequest request, @RequestBody xdUserAi bean){
        //Integer user_key = (Integer) request.getAttribute("USER_KEY");
        bean.setUserId((Integer) request.getAttribute("USER_KEY"));
        bean.setIsDraft(1);
        PageInfo<xdUserAi> outBean = webxdUserServer.draftPageList( bean);
        //int recordCount = webxdUserServer.draftPageListPage(bean);
        //pageBean<xdUserAi> pageBean = new pageBean().setMyList(outBean,recordCount,bean.getPageCount());

        return  ApiResult.success(outBean);
    }

    //用户草稿箱详情
    //视频展示详情
    @AuthToken
    @RequestMapping("/opusDetails")
    public ApiResult opusDetails(HttpServletRequest request, @RequestBody xdWorkbenchBean bean){
        xdWorkbenchBean outBean = webxdUserServer.opusDetails(bean.getId());
        return  ApiResult.success(outBean);
    }


    //用户草稿箱删除(需要支持批量操作)
    //视频展示删除(需要支持批量操作)
    @AuthToken
    @RequestMapping("/draftDel")
    public ApiResult draftDel(HttpServletRequest request, @RequestBody xdUserAi bean){
        int isOut = webxdUserServer.draftDel(bean);
        return  ApiResult.success(null);
    }

    //u3d生成完成后调用修改作品
    @RequestMapping("/draftUpd")
    public ApiResult draftUpd(HttpServletRequest request, @RequestBody xdUserStatisticsBean bean){
        int isOut = webxdUserServer.draftUpd(bean);
        return  ApiResult.success(null);
    }

    //是否有完成视频通知
    @AuthToken
    @RequestMapping("/isInform")
    public ApiResult isInform(HttpServletRequest request, @RequestBody xdUserAi bean){
        Integer isOut = webxdUserServer.isInform((Integer)request.getAttribute("USER_KEY"));
        HashMap<String, Integer> map = new HashMap<>();
        map.put("isInform",isOut);
        return  ApiResult.success(map);
    }

    //平台用户列表(分页)
    @AuthToken
    @RequestMapping("/userPageList")
    public ApiResult userPageList(HttpServletRequest request, @RequestBody xdUser bean){
        PageInfo<xdUser> xdUserList =  webxdUserServer.userPageList(bean);
        return  ApiResult.success(xdUserList);
    }

    //添加平台用户
    @AuthToken
    @RequestMapping("/userInsert")
    public ApiResult userInsert(HttpServletRequest request, @RequestBody xdUser bean){
        int isOut = webxdUserServer.userInsert(bean);
        return  ApiResult.success(bean);
    }

    //删除平台用户
    @AuthToken
    @RequestMapping("/userDel")
    public ApiResult userDel(HttpServletRequest request, @RequestBody xdUser bean){
        int isOut = webxdUserServer.userDel(bean);
        return  ApiResult.success(bean);
    }

    //修改平台用户信息
    @AuthToken
    @RequestMapping("/userUpd")
    public ApiResult userUpd(HttpServletRequest request, @RequestBody xdUser bean){
        xdUser outBean = webxdUserServer.userUpd(bean);
        return  ApiResult.success(bean);
    }

    //重置密码
    @AuthToken
    @RequestMapping("/userReset")
    public ApiResult userReset(HttpServletRequest request, @RequestBody xdUser bean){
        webxdUserServer.userReset(bean);
        return  ApiResult.success(bean);
    }

    //平台用户生成视频时间设置(用于修改用户生成视频付费时间量)
    @AuthToken
    @RequestMapping("/userUpdTim")
    public ApiResult userUpdTim(HttpServletRequest request, @RequestBody xdUserStatistics bean){
        webxdUserServer.userUpdTim(bean);
        return  ApiResult.success(bean);
    }

    //验证用户是否可以生成视频
    @AuthToken
    @RequestMapping("/isCreate")
    public ApiResult isCreate(HttpServletRequest request, @RequestBody xdUserStatistics bean){
        bean.setUserId((Integer) request.getAttribute("USER_KEY"));
        HashMap<String,Integer> isCreate =  webxdUserServer.isCreate(bean);
        return  ApiResult.success(isCreate);
    }

    //用户消息提示
    @AuthToken
    @RequestMapping("/message")
    public ApiResult message(HttpServletRequest request, @RequestBody xdUserStatistics bean){
        bean.setUserId((Integer) request.getAttribute("USER_KEY"));
        HashMap<String,String> message =  webxdUserServer.message(bean);
        return  ApiResult.success(message);
    }

    //统计数据
    @AuthToken
    @RequestMapping("/sumCount")
    public ApiResult sumCount(HttpServletRequest request, @RequestBody xdUserStatistics bean){
        //bean.setUserId((Integer) request.getAttribute("USER_KEY"));
        HashMap<String,String> sumCount =  webxdUserServer.sumCount();
        return  ApiResult.success(sumCount);
    }

}
