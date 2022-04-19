package com.stla.figure.dao;

import com.stla.figure.bean.xdUserStatistics;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface xdUserStatisticsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(xdUserStatistics record);

    int insertSelective(xdUserStatistics record);

    xdUserStatistics selectByPrimaryKey(Integer id);

    xdUserStatistics selectByPrimaryKeyUserId(Integer userId);

    int updateByPrimaryKeySelective(xdUserStatistics record);

    int updRemainingTime(xdUserStatistics record);

    int updateByPrimaryKey(xdUserStatistics record);
}