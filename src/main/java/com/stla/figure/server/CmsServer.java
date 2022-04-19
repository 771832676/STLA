package com.stla.figure.server;

import com.stla.figure.bean.*;
import com.stla.figure.dao.*;
import com.stla.figure.deploy.ApiResult;
import com.stla.figure.utility.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CmsServer {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    authorizationMapper authorizationMapper;

    @Autowired
    contactInformationMapper contactInformationMapper;

    @Autowired
    cmsMapper cmsMapper;

    @Autowired
    cmsCmsOrFileMapper cmsCmsOrFileMapper;

    @Autowired
    resourceFileMapper resourceFileMapper;

    @Value("${fileUrl}")
    private String urlPath;

    public boolean smsSend(smsBean bean) {
        String key = bean.getPhone();
        Object get = redisUtils.get(key);
        if (null == get){//缓存服务器搜索不存在该手机号后发送短信并存储短信60s
            int number = (int)((Math.random()*9+1)*10000);
            String content = "您的验证码是："+ number +"。请不要把验证码泄露给其他人。";
            SMSUtil.sendAuthCode(key,content,"小哆智能");
            boolean ifSet = redisUtils.set(key, number,400);//一天等于86400秒
            return true;
        }else {
            System.out.println(key+"该手机号短信已发送,验证码为:"+get);
            return false;
        }
    }

    //0:短信已失效 1:验证成功符合 2:不一致
    public int smsVerify(smsBean bean) {
        try {
            String key = bean.getPhone();
            Object get = redisUtils.get(key);
            if (null == get){//短信超时时效不存在
                return 0;
            }else{
                if (Integer.parseInt(bean.getAuthCode()) == (Integer) get){
                    return 1;//验证成功
                }else {
                    return 2;//短信存在但是不符合
                }
            }
        }catch (Exception e){
            return 0;
        }
    }

    public int insertContact(contactInformation bean) {
        List<String> iphone = new ArrayList<String>();
        //iphone.add("18501254788");
        iphone.add("16619817752");
        iphone.add("13911805794");
        iphone.add("13331135193");
        //iphone.add("15726657292");
        for (String key : iphone){
            String content = "我在這裡為master服務，您负责小哆虚拟人产品有客户人咨询啦。请您通过后台及时查看哟~";
            SMSUtil.sendAuthCode(key,content,"小哆智能");
        }
        return contactInformationMapper.insertSelective(bean);
    }

    public List<cms> contactsList() {
        return contactInformationMapper.contactsList();
    }

    public contactInformation contactsListBean(contactInformation bean) {
        return contactInformationMapper.selectByPrimaryKey(bean.getId());
    }

    public List<cms> cmsList(cms bean) {
       return cmsMapper.cmsList(bean);
    }

    public int cmsUpd(cms bean) {
        int i = cmsMapper.updateByPrimaryKeySelective(bean);
        if (null != bean.getFileMd5() && bean.getFileMd5().size() != 0){
            for (cmsOrFile md5Bean: bean.getFileMd5()){
                resourceFile resourceFile = resourceFileMapper.selectByPrimaryKeyMd5(md5Bean.getFileMd5());
                if (null != resourceFile){
                    cmsCmsOrFile cmsCmsOrFile = new cmsCmsOrFile();
                    cmsCmsOrFile.setFileTitle(md5Bean.getFileTitle());
                    cmsCmsOrFile.setCmsId(bean.getId());
                    cmsCmsOrFile.setFileId(resourceFile.getId());
                    int i1 = cmsCmsOrFileMapper.insertSelective(cmsCmsOrFile);
                }
            }
        }
        return 1;
    }

    public cms cmsBean(cms bean) {
        cms cms = cmsMapper.selectByPrimaryKey(bean.getId());
        List<cmsCmsOrFile> cmsCmsOrFilesList = cmsCmsOrFileMapper.selectByPrimaryKeyMd5(bean.getId());
        if (null != cmsCmsOrFilesList && cmsCmsOrFilesList.size() != 0){
            for (cmsCmsOrFile cmsCmsOrFiles :cmsCmsOrFilesList){
                resourceFile resourceFile = resourceFileMapper.selectByPrimaryKey(cmsCmsOrFiles.getFileId());
                cmsCmsOrFiles.setUrl(urlPath+resourceFile.getUrl());
            }
            cms.setCmsCmsOrFileList(cmsCmsOrFilesList);
        }
        return cms;
    }

    public int cmsDel(Integer id) {
        return cmsMapper.deleteByPrimaryKey(id);
    }

    public int cmsIns(cms bean) {
        Integer i = cmsMapper.insertSelective(bean);
        if (null != bean.getFileMd5() && bean.getFileMd5().size() != 0){
            for (cmsOrFile md5Bean: bean.getFileMd5()){
                resourceFile resourceFile = resourceFileMapper.selectByPrimaryKeyMd5(md5Bean.getFileMd5());
                if (null != resourceFile){
                    cmsCmsOrFile cmsCmsOrFile = new cmsCmsOrFile();
                    cmsCmsOrFile.setFileTitle(md5Bean.getFileTitle());
                    cmsCmsOrFile.setCmsId(bean.getId());
                    cmsCmsOrFile.setFileId(resourceFile.getId());
                    int i1 = cmsCmsOrFileMapper.insertSelective(cmsCmsOrFile);
                }
            }
        }
        return 1;
    }
}
