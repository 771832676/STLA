package com.stla.figure.dao;

import com.stla.figure.bean.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface xdAiLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(xdAiLog record);

    int insertSelective(xdAiLog record);

    xdAiLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(xdAiLog record);

    int updateByPrimaryKey(xdAiLog record);

    List<xdAiLog> loge();
}