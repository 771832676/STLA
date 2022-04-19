package com.stla.figure.dao;

import com.stla.figure.bean.xdAi;
import com.stla.figure.bean.xdAiResource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface xdAiResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(xdAiResource record);

    int insertSelective(xdAiResource record);

    xdAiResource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(xdAiResource record);

    int updateByPrimaryKey(xdAiResource record);

    List<xdAiResource> resourceList(Integer aiId);
}