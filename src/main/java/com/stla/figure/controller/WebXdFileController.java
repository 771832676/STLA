package com.stla.figure.controller;

import com.stla.figure.bean.resourceFile;
import com.stla.figure.bean.xdUserAiBean;
import com.stla.figure.deploy.ApiResult;
import com.stla.figure.deploy.AuthFileResult;
import com.stla.figure.deploy.AuthToken;
import com.stla.figure.server.FileService;
import com.stla.figure.server.WebXdFileServer;
import com.stla.figure.utility.DateTools;
import com.stla.figure.utility.DownLoad;
import com.stla.figure.utility.MD5;
import com.stla.figure.utility.SimpleUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

//小哆webfile
@RestController
@RequestMapping("/xdfile")
public class WebXdFileController {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(WebXdFileController.class);

    @Autowired
    WebXdFileServer webXdFileServer;

    @Resource
    private FileService fileService;

    SimpleUpload simpleUpload;;


    @Value("${fileUrl}")
    private String urlPath;

    @Value("${qiniuMain}")
    private String qiniuMain;

    

    private String basePath = "files";
    private String tempPath = System.getProperty("java.io.tmpdir");

    //七牛上传/需要后期优化多线程方案
    @AuthToken
    @RequestMapping("/uploadFile")
    public ApiResult uploadFile(HttpServletRequest request,@RequestParam("file") MultipartFile file){
        File tempFile = null;
        try {
            AuthFileResult authFileResult = new AuthFileResult();
            String fileUuid = UUID.randomUUID().toString().replaceAll("-", "");
            String fileName = fileUuid + "." + StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
            tempFile = new File(tempPath + File.separator + fileName);
            file.transferTo(tempFile);
            String fileMd5 = MD5.md5(tempFile);

            //根据MD5查询数据库文件
            resourceFile resourceFileResult = fileService.findResourceFileResultByMd5(fileMd5);
            //ResourceFileResult resourceFileResult = new ResourceFileResult();

            if (resourceFileResult == null) {
                //  首次上传，先保存文件到指定文件目录，再插入数据库文件表
                //File targetFile = new File(basePath + File.separator + fileName);
                //FileUtils.copyFile(tempFile, targetFile);
                //String url =  DownLoad.downloadUrl(qiniuMain+"/"+fileName);
                String url = qiniuMain+fileName;
                simpleUpload.upload(tempFile,fileName);
                resourceFileResult = new resourceFile();
                resourceFileResult.setLenght(tempFile.length());
                resourceFileResult.setMd5(fileMd5);
                resourceFileResult.setName(file.getOriginalFilename());
                resourceFileResult.setUrl(url);
                resourceFileResult.setUuid(fileUuid);
                //存储到数据库
                fileService.saveResourceFile(resourceFileResult);
            }
            //  已经上传过，直接从数据库文件表读取相关信息
            authFileResult.setFileId(resourceFileResult.getId()+"");
            authFileResult.setFileMd5(resourceFileResult.getMd5());
            authFileResult.setFileUrl(resourceFileResult.getUrl());
            authFileResult.setFileName(file.getOriginalFilename());
            authFileResult.setLenght(resourceFileResult.getLenght());
            return ApiResult.success(authFileResult);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ApiResult.fail("上传失败");
        } finally {
            if (tempFile != null) {
                try {
                    FileUtils.forceDelete(tempFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }




}
