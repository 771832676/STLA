package com.stla.figure.dao;

import com.stla.figure.bean.cms;
import com.stla.figure.bean.contactInformation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface contactInformationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(contactInformation record);

    int insertSelective(contactInformation record);

    contactInformation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(contactInformation record);

    int updateByPrimaryKey(contactInformation record);

    List<cms> contactsList();
}