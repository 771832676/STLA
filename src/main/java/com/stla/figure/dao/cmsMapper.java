package com.stla.figure.dao;

import com.stla.figure.bean.cms;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface cmsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(cms record);

    int insertSelective(cms record);

    cms selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(cms record);

    int updateByPrimaryKey(cms record);

    List<cms> cmsList(cms bean);
}