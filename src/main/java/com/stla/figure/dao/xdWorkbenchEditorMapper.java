package com.stla.figure.dao;

import com.stla.figure.bean.xdWorkbenchEditor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface xdWorkbenchEditorMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByPrimaryKeyEleId(Integer id);

    int insert(xdWorkbenchEditor record);

    int insertSelective(xdWorkbenchEditor record);

    xdWorkbenchEditor selectByPrimaryKey(Integer id);

    List<xdWorkbenchEditor> selectByPrimaryKeyList(Integer id);

    int updateByPrimaryKeySelective(xdWorkbenchEditor record);

    int updateByPrimaryKey(xdWorkbenchEditor record);
}