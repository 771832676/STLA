package com.stla.figure.dao;

import com.stla.figure.bean.xdWorkbenchAi;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface xdWorkbenchAiMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByPrimaryKeyEleId(Integer id);

    int insert(xdWorkbenchAi record);

    int insertSelective(xdWorkbenchAi record);

    xdWorkbenchAi selectByPrimaryKey(Integer id);

    List<xdWorkbenchAi> selectByPrimaryKeyList(Integer id);

    int updateByPrimaryKeySelective(xdWorkbenchAi record);

    int updateByPrimaryKey(xdWorkbenchAi record);
}