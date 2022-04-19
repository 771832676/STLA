package com.stla.figure.server;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stla.figure.bean.*;
import com.stla.figure.dao.authorizationMapper;
import com.stla.figure.deploy.ApiResult;
import com.stla.figure.utility.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class FigureServer {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    authorizationMapper authorizationMapper;

    //生成体验码,没有直接正式的码后期需要增加批量生成<20210918>
    public String Accredit(authorization bean) {
        try {
            bean.setApiToken("");
            int i = authorizationMapper.insertSelective(bean);
            //id,产品编号,授权公司名称,授权开始时间,授权结束时间,是否体验用户,联系人名称,联系人电话<2021-09-17废弃时间>
            String json =bean.getId()+","+ bean.getCode()+","+bean.getCompanyName()+","
                    //+DateTools.dateToString(bean.getStartTime(),"yyyy-MM-dd HH:mm:ss") +","
                    //+DateTools.dateToString(bean.getEndTime(),"yyyy-MM-dd HH:mm:ss")  +","
                    +bean.getIsOfficial()
                    +","+bean.getContactName()+","+bean.getContactPhone();
            String Accredit = DESUtils.encrypt(json);

            authorization updBean = new authorization();
            updBean.setId(bean.getId());
            updBean.setApiToken(Accredit);
            authorizationMapper.updateByPrimaryKeySelective(updBean);
            return Accredit;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "生成密钥失败";
    }


    //通过前端的主键修改为正式版(这里修改正版状态和授权时间)同时生成正版静态密钥
    public String updAccredit(authorization bean) {
        authorization authorization = authorizationMapper.selectByPrimaryKey(bean.getId());
        //id,产品编号,授权公司名称,是否体验用户,授权开始时间,授权结束时间,联系人名称,联系人电话
        String json = bean.getId() +","+authorization.getCode()+","+authorization.getCompanyName()+","
                +bean.getIsOfficial()+","
                //+DateTools.dateToString(authorization.getStartTime(),"yyyy-MM-dd HH:mm:ss")+","
                //+DateTools.dateToString(bean.getEndTime(),"yyyy-MM-dd HH:mm:ss")+","
                +authorization.getContactName()+","+authorization.getContactPhone();
        try {
            String Accredit = DESUtils.encrypt(json);
            authorization.setApiToken(Accredit);
            authorization.setIsOfficial(bean.getIsOfficial());
            authorization.setUpdateTime(new Date());
            authorization.setEndTime(bean.getEndTime());
            int i = authorizationMapper.updateByPrimaryKeySelective(authorization);
            return Accredit;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "生成密钥失败";
    }

    public ApiResult getCode(authorizationApi bean) {
        ApiResult apiResult = new ApiResult();
        try {
            //解密
            String decrypt = DESUtils.decrypt(bean.getApiToken());
            //返回1:成功 2:设备绑定不一致 3:授权时间已过期
            int isoff = IsOfficial(decrypt, bean.getDeviceCode(), bean.getApiToken());
            if (isoff == 1){
                decrypt = decrypt+","+bean.getDeviceCode()+","+DateTools.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss");//解码静态令牌后追加设备码,服务器当前时间
                //String AesCode = AESUtils.Encrypt(decrypt, "stladjmjszyzancc");
                String uuid = UUIDGenerator.get();
                //redis存储
                boolean ifSet = redisUtils.set(uuid, decrypt,604800);//一天等于86400秒-此处存放7天
                System.out.println("redis存储状态"+ifSet);
                HashMap<String, String> map = new HashMap<>();
                map.put("apiCode",uuid);//24h令牌码
                return apiResult.success(map);
            }else if (isoff == 2){
                return apiResult.fail("生成临时令牌失败,绑定设备不一致!");
            }else if (isoff == 3){
                return apiResult.fail("生成临时令牌失败,授权时间已过期!");
            }else if (isoff == 4){
                return apiResult.fail("未发现授权数据,请重新申请密钥!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiResult.fail("生成临时令牌失败");
    }

    //方法判断授权设备是否是同一台不是false 是true
    //并对首次认证进行设备绑定
    //返回1:成功 2:设备绑定不一致 3:授权时间已过期
    public int IsOfficial(String decrypt,String deviceCode,String apiToken){//解码后文件,机器码,静态授权码
        List<String> result = Arrays.asList(decrypt.split(","));
        //id,产品编号,授权公司名称,是否体验用户,授权开始时间,授权结束时间,联系人名称,联系人电话
        authorization Bean = authorizationMapper.selectByPrimaryKey(Integer.parseInt(result.get(0)));
        if (Bean != null){
            if (1 == Bean.getIsOfficial()){//1正式用户 0体验用户
                if (null != Bean.getDeviceCode()){
                    if ( Bean.getDeviceCode().equals(deviceCode)){
                        return 1;
                    }else {
                        return 2;
                    }
                }else{
                    Bean.setDeviceCode(deviceCode);
                    Bean.setUpdateTime(new Date());
                    authorizationMapper.updateByPrimaryKeySelective(Bean);
                    return 1;
                }
            }else {
                int i = DateTools.compare_date(DateTools.dateToString(Bean.getEndTime(), "yyyy-MM-dd HH:mm:ss"), DateTools.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
                if (i == 2){
                    return 3;
                }
            }
        }else{
            return 4;
        }

        return 1;
    }

    public boolean
    isExist(authorizationApi bean) {
        try {
            //redis 获取是否存在24h Code
            String get = (String) redisUtils.get(bean.getApiCode());
            if (null == get || "".equals(get)){
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Long expire = redisUtils.getExpire(bean.getApiCode());
        if (expire < 345600){//小于4天会重置时间
            redisUtils.expire(bean.getApiCode(),604800);//一天等于86400秒-此处重置存放7天
        }
        return true;
    }

    public void accreditUpdate(authorization bean) {
        int i = authorizationMapper.updateByPrimaryKeySelective(bean);
    }

    public authorization accreditDetails(Integer id) {
        return authorizationMapper.selectByPrimaryKey(id);
    }

    public void accreditDelete(Integer id) {
        authorizationMapper.accreditDelete(id);
    }

    public PageInfo<authorization> getList(authorization newBean) {
        PageHelper.startPage(newBean.getCurPage(),newBean.getPageCount());
        List<authorization> settingList = authorizationMapper.accreditLisPage(newBean);
        PageInfo<authorization> pageInfo = new PageInfo<>(settingList);
        return pageInfo;
    }

    public void updDeviceCode(authorization bean) {
        int i = authorizationMapper.updDeviceCode(bean.getId());
    }

    public void setRedis(redisBean redis) {
        boolean ifSet = redisUtils.set(redis.getKey(), redis.getValue(),86400);//一天等于86400秒
    }
}
