package com.stla.figure.dao;

import com.stla.figure.bean.resourceFile;
import com.stla.figure.deploy.ResourceFileResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface resourceFileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(resourceFile record);

    int insertSelective(resourceFile record);

    resourceFile selectByPrimaryKey(Integer id);

    resourceFile selectByPrimaryKeyMd5(String md5);

    int updateByPrimaryKeySelective(resourceFile record);

    int updateByPrimaryKey(resourceFile record);

    resourceFile findResourceFileResultByMd5(String fileMd5);

    List<resourceFile> getList(List<String> fileMd5);
}