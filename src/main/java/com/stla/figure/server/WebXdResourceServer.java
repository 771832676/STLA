package com.stla.figure.server;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stla.figure.bean.xdAi;
import com.stla.figure.bean.xdAiResource;
import com.stla.figure.bean.xdResourceSetting;
import com.stla.figure.bean.xdUserAi;
import com.stla.figure.dao.xdAiMapper;
import com.stla.figure.dao.xdAiResourceMapper;
import com.stla.figure.dao.xdResourceSettingMapper;
import com.stla.figure.utility.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class WebXdResourceServer {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    xdAiMapper xdAiMapper;

    @Autowired
    xdAiResourceMapper xdAiResourceMapper;

    @Autowired
    xdResourceSettingMapper xdResourceSettingMapper;


    public HashMap<String, List> resourceBean(xdAiResource bean) {

        List<xdAiResource> resourceBean = xdAiResourceMapper.resourceList(bean.getAiId());
        List<xdAiResource> aiType1 = new ArrayList<>();
        List<xdAiResource> aiType2 = new ArrayList<>();
        List<xdAiResource> aiType3 = new ArrayList<>();
        for (xdAiResource xdAiResource: resourceBean) {
            switch (xdAiResource.getAiType()){
                case 1 :
                    aiType1.add(xdAiResource);
                    break;
                case 2 :
                    aiType2.add(xdAiResource);
                    break;
                case 3 :
                    aiType3.add(xdAiResource);
                    break;
            }
        }
        HashMap<String, List> map = new HashMap<>();
        map.put("resourceType1",aiType1);
        map.put("resourceType2",aiType2);
        map.put("resourceType3",aiType3);
        return map;
    }

    public List<xdAi> aiList( ) {
        List<xdAi> aiList = xdAiMapper.aiList();
        return aiList;
    }

    public int aiAdd(xdAi bean) {
        bean.setCreateTime(new Date());
        int i = xdAiMapper.insertSelective(bean);
        return i;
    }

    public int aiUpd(xdAi bean) {
        bean.setUpdateTime(new Date());
        int i = xdAiMapper.updateByPrimaryKeySelective(bean);
        return i;
    }

    public int aiDel(xdAi bean) {
        bean.setUpdateTime(new Date());
        bean.setIsDelete(1);
        int i = xdAiMapper.updateByPrimaryKeySelective(bean);
        return i;
    }

    public xdAiResource resourceAdd(xdAiResource bean) {
        bean.setCreateTime(new Date());
        int i = xdAiResourceMapper.insertSelective(bean);
        return bean;
    }

    public xdAiResource resourceUpd(xdAiResource bean) {
        bean.setUpdateTime(new Date());
        int i = xdAiResourceMapper.updateByPrimaryKeySelective(bean);
        return bean;
    }

    public int resourceDel(xdAiResource bean) {
        bean.setUpdateTime(new Date());
        bean.setIsDelete(1);
        int i = xdAiResourceMapper.updateByPrimaryKeySelective(bean);
        return i;
    }

    public PageInfo<xdResourceSetting> settingList(xdResourceSetting bean) {
        PageHelper.startPage(bean.getCurPage(),bean.getPageCount());
        List<xdResourceSetting> settingList = xdResourceSettingMapper.settingList(bean);
        PageInfo<xdResourceSetting> pageInfo = new PageInfo<>(settingList);
        return pageInfo;
    }

    public List<xdResourceSetting> settingListOpt(xdResourceSetting bean) {
        List<xdResourceSetting> settingList = xdResourceSettingMapper.settingListOpt(bean.getUserId());
        return settingList;
    }

    public int settingAdd(xdResourceSetting bean) {
        bean.setCreateTime(new Date());
        int i = xdResourceSettingMapper.insertSelective(bean);
        return i;
    }

    public int settingDel(xdResourceSetting bean) {
        String[] ids = bean.getIds().split(",");
        for (String id : ids) {
            bean.setId(Integer.parseInt(id));
            bean.setUpdateTime(new Date());
            bean.setIsDelete(1);
            int i = xdResourceSettingMapper.updateByPrimaryKeySelective(bean);
        }
        return 1;
    }

    public Double settingSum(xdResourceSetting bean) {
        Double sum = xdResourceSettingMapper.settingSum(bean.getUserId());
        return sum;
    }
}
