package com.stla.figure.dao;

import com.stla.figure.bean.xdWorkbench;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface xdWorkbenchMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(xdWorkbench record);

    int insertSelective(xdWorkbench record);

    xdWorkbench selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(xdWorkbench record);

    int updateByPrimaryKey(xdWorkbench record);
}