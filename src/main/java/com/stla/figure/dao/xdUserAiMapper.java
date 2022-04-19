package com.stla.figure.dao;

import com.stla.figure.bean.xdUserAi;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface xdUserAiMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(xdUserAi record);

    int insertSelective(xdUserAi record);

    xdUserAi selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(xdUserAi record);

    int updateByPrimaryKey(xdUserAi record);

    List<xdUserAi> draftPageList(xdUserAi bean);

}