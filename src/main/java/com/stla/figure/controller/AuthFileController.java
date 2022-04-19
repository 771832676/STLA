package com.stla.figure.controller;

import com.stla.figure.bean.resourceFile;
import com.stla.figure.deploy.ApiResult;
import com.stla.figure.deploy.AuthFileIO;
import com.stla.figure.deploy.AuthFileResult;
import com.stla.figure.deploy.ResourceFileResult;
import com.stla.figure.server.FileService;
import com.stla.figure.utility.MD5;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class AuthFileController {

    @Resource
    private FileService fileService;

    private String basePath = "files";
    private String tempPath = System.getProperty("java.io.tmpdir");

    @Value("${fileUrl}")
    private String urlPath;

    // @ApiOperation(value = "上传文件", notes = "上传文件", response = AuthFileResult.class)
    @PostMapping("/uploadFile")
    public ApiResult uploadFile(@RequestParam("file") MultipartFile file) {
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
                File targetFile = new File(basePath + File.separator + fileName);
                FileUtils.copyFile(tempFile, targetFile);
                resourceFileResult = new resourceFile();
                resourceFileResult.setLenght(tempFile.length());
                resourceFileResult.setMd5(fileMd5);
                resourceFileResult.setName(file.getOriginalFilename());
                resourceFileResult.setUrl(fileName);
                resourceFileResult.setUuid(fileUuid);
                //存储到数据库
                fileService.saveResourceFile(resourceFileResult);
            }
            //  已经上传过，直接从数据库文件表读取相关信息
            authFileResult.setFileId(resourceFileResult.getId()+"");
            authFileResult.setFileMd5(resourceFileResult.getMd5());
            authFileResult.setFileUrl(urlPath+resourceFileResult.getUrl());
            authFileResult.setFileName(file.getOriginalFilename());
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


   // @ApiOperation(value = "上传多个文件", notes = "上传多个文件", response = AuthFileResult.class)
    @PostMapping("/uploadFiles")
    public ApiResult uploadFiles(@RequestParam("files") MultipartFile[] files) {
        List<AuthFileResult> results = new ArrayList<>();
        for (MultipartFile file: files) {
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
                    File targetFile = new File(basePath + File.separator + fileName);
                    FileUtils.copyFile(tempFile, targetFile);
                    resourceFileResult = new resourceFile();
                    resourceFileResult.setLenght(tempFile.length());
                    resourceFileResult.setMd5(fileMd5);
                    resourceFileResult.setName(file.getOriginalFilename());
                    resourceFileResult.setUrl(urlPath+fileName);
                    resourceFileResult.setUuid(fileUuid);
                    //存储到数据库
                    fileService.saveResourceFile(resourceFileResult);
                }
                //  已经上传过，直接从数据库文件表读取相关信息
                authFileResult.setFileId(resourceFileResult.getId()+"");
                authFileResult.setFileMd5(resourceFileResult.getMd5());
                authFileResult.setFileUrl(resourceFileResult.getUrl());
                authFileResult.setFileName(file.getOriginalFilename());
                results.add(authFileResult);
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
        return ApiResult.success(results);
    }

    //@ApiOperation(value = "检查文件", notes = "检查文件", response = AuthFileResult.class)
    @PostMapping("/checkFile")
    public ApiResult checkFile(@RequestBody AuthFileIO io) {
        //根据MD5查询数据库文件
        resourceFile resourceFileResult = fileService.findResourceFileResultByMd5(io.getFileMd5());
        if (resourceFileResult != null) {
            //  已经上传过，直接从数据库文件表读取相关信息
            AuthFileResult authFileResult = new AuthFileResult();
            authFileResult.setFileId(resourceFileResult.getId()+"");
            authFileResult.setFileMd5(resourceFileResult.getMd5());
            authFileResult.setFileUrl(urlPath+resourceFileResult.getUrl());
            authFileResult.setFileName(resourceFileResult.getName());
            return ApiResult.success(authFileResult);
        }
        return ApiResult.success();
    }

    //@ApiOperation(value = "检查文件", notes = "检查文件，使用逗号分隔md5", response = AuthFileResult.class)
    @PostMapping("/checkFiles")
    public ApiResult checkFiles(@RequestBody  AuthFileIO io) {
        //根据MD5查询数据库文件
        List<resourceFile> fileResultList = fileService.getList( Arrays.asList(io.getFileMd5().split(",")));
        if (CollectionUtils.isNotEmpty(fileResultList)) {
            //  已经上传过，直接从数据库文件表读取相关信息
            List<AuthFileResult> resultList = new ArrayList<>();
            for (resourceFile resourceFileResult : fileResultList) {
                AuthFileResult authFileResult = new AuthFileResult();
                authFileResult.setFileId(resourceFileResult.getId()+"");
                authFileResult.setFileMd5(resourceFileResult.getMd5());
                authFileResult.setFileUrl(urlPath+resourceFileResult.getUrl());
                authFileResult.setFileName(resourceFileResult.getName());
                resultList.add(authFileResult);
            }
            return ApiResult.success(resultList);
        }
        return ApiResult.success();
    }

    @PostMapping("/fileDel")
    public ApiResult fileDel(@RequestBody  AuthFileResult io) {
        int isDel= fileService.fileDel(io.getFileId());
        return ApiResult.success();
    }

}
