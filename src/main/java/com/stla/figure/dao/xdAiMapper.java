package com.stla.figure.dao;

import com.stla.figure.bean.xdAi;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface xdAiMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(xdAi record);

    int insertSelective(xdAi record);

    xdAi selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(xdAi record);

    int updateByPrimaryKey(xdAi record);

    List<xdAi> aiList();
}