package com.stla.figure.dao;

import com.stla.figure.bean.xdUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface xdUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(xdUser record);

    Integer insertSelective(xdUser record);

    xdUser selectByPrimaryKey(Integer id);

    xdUser selectByPrimaryUserName(String userName);

    int updateByPrimaryKeySelective(xdUser record);

    int updateByPrimaryKey(xdUser record);

    List<xdUser> userPageList(xdUser bean);

    HashMap<String, String> sumCount();
}