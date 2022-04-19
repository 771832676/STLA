package com.stla.figure.controller;

import com.stla.figure.bean.*;
import com.stla.figure.deploy.ApiResult;
import com.stla.figure.server.CmsServer;
import com.stla.figure.server.FigureServer;
import com.stla.figure.utility.ObjectUtils;
import com.stla.figure.utility.PageUtil;
import com.stla.figure.utility.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/cms")
public class CMSController {


    @Autowired
    CmsServer CmsServer;

    //cms-获取随机验证码
    @RequestMapping("/getRandom")
    public ApiResult getRandom(@RequestBody Object bean){
        HashMap<String, String> map = new HashMap<>();
        map.put("random",""+RandomUtil.getRandom(5));
        return  ApiResult.success(map);
    }

    //cms-发送短信-redis-60s存活提示-不存活发送手机号为key-随机数字为value-存活60s
    @RequestMapping("/smsSend")
    public ApiResult smsSend(@RequestBody smsBean bean){
        boolean isSms = CmsServer.smsSend(bean);
        HashMap<String, String> map = new HashMap<>();
        map.put("isSms",""+isSms);
        return  ApiResult.success(map);
    }

    //cms-验证短信合法-手机号为key-随机数字为value-存活60s
    @RequestMapping("/smsVerify")
    public ApiResult smsVerify(@RequestBody smsBean bean){
        int smsCode = CmsServer.smsVerify(bean);
        HashMap<String, String> map = new HashMap<>();
        map.put("smsCode",""+smsCode);
        return  ApiResult.success(map);
    }

    //cms-保存联系人信息
    @RequestMapping("/insertContact")
    public ApiResult insertContact(@RequestBody contactInformation bean){
        bean.setCreateTime(new Date());
        int smsCode = CmsServer.insertContact(bean);
        return  ApiResult.success(null);
    }

    //cms-联系人列表-时间倒序
    @RequestMapping("/contactsList")
    public ApiResult contactsList(@RequestBody pageBean bean){
       authorization newBean = new authorization();
        List<cms> cmsList = CmsServer.contactsList();
        PageUtil<cms> userPageUtil = new PageUtil<>();
        userPageUtil.setPageNum(bean.getCurPage());
        userPageUtil.setPageSize(bean.getPageCount());
        PageUtil<cms> pageList = userPageUtil.setMyList(cmsList);
        pageList.getMyList().clear();
        return  ApiResult.success(pageList);
    }
    //cms-联系人详情
    @RequestMapping("/contactsListBean")
    public ApiResult contactsListBean(@RequestBody contactInformation bean){
        contactInformation contactsListBean = CmsServer.contactsListBean(bean);
        return  ApiResult.success(contactsListBean);
    }
    //cms-列表
    @RequestMapping("/cmsList")
    public ApiResult cmsList(@RequestBody cms bean){
        List<cms> list = CmsServer.cmsList(bean);
        return  ApiResult.success(list);
    }
    //cms-添加/
    @RequestMapping("/cmsIns")
    public ApiResult cmsIns(@RequestBody cms bean){
        int cmsBean =  CmsServer.cmsIns(bean);
        return  ApiResult.success(cmsBean);
    }
    //cms-编辑
    @RequestMapping("/cmsUpd")
    public ApiResult cmsUpd(@RequestBody cms bean){
        int cmsBean =  CmsServer.cmsUpd(bean);
        return  ApiResult.success(cmsBean);
    }
    //cms-详情
    @RequestMapping("/cmsBean")
    public ApiResult cmsBean(@RequestBody cms bean){
        cms cmsBean = CmsServer.cmsBean(bean);
        return  ApiResult.success(cmsBean);
    }
    //cms-删除
    @RequestMapping("/cmsDel")
    public ApiResult cmsDel(@RequestBody cms bean){
        int cmsDel =CmsServer.cmsDel(bean.getId());
        return  ApiResult.success(cmsDel);
    }

}
