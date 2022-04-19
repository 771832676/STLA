package com.stla.figure.dao;

import com.stla.figure.bean.cmsCmsOrFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface cmsCmsOrFileMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByPrimaryKeyId(Integer id);

    int insert(cmsCmsOrFile record);

    int insertSelective(cmsCmsOrFile record);

    cmsCmsOrFile selectByPrimaryKey(Integer id);

    List<cmsCmsOrFile> selectByPrimaryKeyMd5(Integer id);

    int updateByPrimaryKeySelective(cmsCmsOrFile record);

    int updateByPrimaryKey(cmsCmsOrFile record);
}