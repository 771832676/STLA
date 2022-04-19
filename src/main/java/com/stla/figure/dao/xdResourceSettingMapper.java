package com.stla.figure.dao;

import com.stla.figure.bean.xdResourceSetting;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface xdResourceSettingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(xdResourceSetting record);

    int insertSelective(xdResourceSetting record);

    xdResourceSetting selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(xdResourceSetting record);

    int updateByPrimaryKey(xdResourceSetting record);

    List<xdResourceSetting> settingList(xdResourceSetting userId);

    List<xdResourceSetting> settingListOpt(Integer userId);

    Double settingSum(Integer userId);
}