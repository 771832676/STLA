package com.stla.figure.server;

import com.stla.figure.bean.resourceFile;
import com.stla.figure.dao.cmsCmsOrFileMapper;
import com.stla.figure.dao.resourceFileMapper;
import com.stla.figure.deploy.ResourceFileResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    @Autowired
    resourceFileMapper resourceFileMapper;

    @Autowired
    com.stla.figure.dao.cmsCmsOrFileMapper cmsCmsOrFileMapper;


    public resourceFile findResourceFileResultByMd5(String fileMd5) {
        resourceFile bean =  resourceFileMapper.findResourceFileResultByMd5(fileMd5);
        return bean;
    }

    public int saveResourceFile(resourceFile bean) {
        return resourceFileMapper.insert(bean);
    }

    public List<resourceFile> getList(List<String> fileMd5s) {
        return resourceFileMapper.getList(fileMd5s);
    }

    public int fileDel(String fileId) {
        cmsCmsOrFileMapper.deleteByPrimaryKeyId(Integer.parseInt(fileId));
       return resourceFileMapper.deleteByPrimaryKey(Integer.parseInt(fileId));
    }
}
