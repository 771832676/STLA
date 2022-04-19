package com.stla.figure.dao;

import com.stla.figure.bean.authorization;
import com.stla.figure.bean.pageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface authorizationMapper {
    int deleteByPrimaryKey(Integer id);

    String insert(authorization record);

    int insertSelective(authorization record);

    authorization selectByPrimaryKey(Integer id);

    authorization selectByApiCode(String apiCode);

    int updateByPrimaryKeySelective(authorization record);

    int updateByPrimaryKey(authorization record);

    List<authorization> accreditLisPage(authorization newBean);

    int accreditDelete(Integer id);

    int updDeviceCode(Integer id);
}