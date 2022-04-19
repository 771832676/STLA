package com.stla.figure.server;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stla.figure.bean.*;
import com.stla.figure.dao.*;
import com.stla.figure.utility.AESUtils;
import com.stla.figure.utility.DateTools;
import com.stla.figure.utility.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class WebxdUserServer {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    xdUserMapper xdUserMapper;

    @Autowired
    xdUserStatisticsMapper xdUserStatisticsMapper;

    @Autowired
    xdUserAiMapper xdUserAiMapper;

    @Autowired
    xdWorkbenchMapper xdWorkbenchMapper;

    @Autowired
    xdWorkbenchAiMapper xdWorkbenchAiMapper;

    @Autowired
    xdWorkbenchEditorMapper xdWorkbenchEditorMapper;

    @Value("${loginTimRedis}")
    private Integer loginTimRedis;

    @Value("${qiniuDownLoad}")
    private String qiniuDownLoad;

    public xdUser information(xdUser bean) {
        xdUser outBean = xdUserMapper.selectByPrimaryKey(bean.getId());
        return outBean;
    }

    public HashMap login(xdUser bean) throws Exception {
        HashMap<String, String> map = new HashMap<>();
        xdUser user = xdUserMapper.selectByPrimaryUserName(bean.getUserName());
        if (null == user){
            map.put("isPassword","false");
            map.put("msg","用户名不存在");
        }else{
            if (StringUtils.isNotBlank(bean.getPassword())){
                if (bean.getPassword().equals(user.getPassword())){
                    if (user.getApprovalState() == 1){
                        String token = UUID.randomUUID().toString().replaceAll("-", "");
                        String tokens = user.getId()+","+ user.getUserName()+","+user.getPassword();
                        String aesToken = AESUtils.Encrypt(tokens,"stladjmjszyzancc");
                        map.put("isPassword","true");
                        map.put("msg","登录成功");
                        map.put("token",token);
                        map.put("id",user.getId().toString());
                        boolean ifSet = redisUtils.set(token, aesToken,loginTimRedis);//半小时
                    }else{
                        map.put("isPassword","false");
                        map.put("msg","失败:"+user.getRemark());
                    }

                }else {
                    map.put("isPassword","false");
                    map.put("msg","密码错误");
                }
            }else {
                map.put("isPassword","false");
                map.put("msg","密码不可为空");
            }
        }

        return map;
    }

    public HashMap loginVerify(xdUser bean) {
        HashMap<String, String> map = new HashMap<>();
        if (StringUtils.isNotBlank(bean.getToken())){
            Object get = redisUtils.get(bean.getToken());
            if (null != get){
                map.put("isToken","true");
                map.put("msg","成功");
            }else{
                map.put("isToken","false");
                map.put("msg","token已失效,请重新登录");
            }
        }else {
            map.put("isToken","false");
            map.put("msg","token不可为空");
        }
        return map;
    }

    public xdUserStatistics statistics(xdUser bean) {
        xdUserStatistics outBean = xdUserStatisticsMapper.selectByPrimaryKeyUserId(bean.getId());
        return outBean;
    }

    public int userInsert(xdUser bean) {
        bean.setCreateTime(new Date());
        xdUserMapper.insertSelective(bean);

        xdUserStatistics sBean = new xdUserStatistics();
        sBean.setUserId(bean.getId());
        sBean.setCreateTime(new Date());
        xdUserStatisticsMapper.insertSelective(sBean);

        return 1;
    }

    public int userDel(xdUser bean) {
        bean.setIsDelete(1);
        bean.setUpdateTime(new Date());
        xdUserMapper.updateByPrimaryKeySelective(bean);
        return 1;
    }

    public xdUser userUpd(xdUser bean) {
        bean.setUpdateTime(new Date());
        int i = xdUserMapper.updateByPrimaryKeySelective(bean);
        return bean;
    }

    public xdUser userReset(xdUser bean) {
        xdUser userbean = new xdUser();
        userbean.setUpdateTime(new Date());
        userbean.setPassword("123456");
        userbean.setId(bean.getId());
        int i = xdUserMapper.updateByPrimaryKeySelective(userbean);
        return null;
    }

    public void userUpdTim(xdUserStatistics bean) {
        bean.setUpdateTime(new Date());
        xdUserStatisticsMapper.updRemainingTime(bean);
    }

    public PageInfo<xdUser> userPageList(xdUser bean) {
        PageHelper.startPage(bean.getCurPage(),bean.getPageCount());
        List<xdUser> pagelist = xdUserMapper.userPageList(bean);
        PageInfo<xdUser> pageInfo = new PageInfo<>(pagelist);
        return pageInfo;
    }

    public  PageInfo<xdUserAi> draftPageList(xdUserAi bean) {
        PageHelper.startPage(bean.getCurPage(),bean.getPageCount());
        List<xdUserAi> pagelist = xdUserAiMapper.draftPageList(bean);
        PageInfo<xdUserAi> pageInfo = new PageInfo<>(pagelist);
        return pageInfo;
    }

    public int draftDel(xdUserAi bean) {
        if (null != bean.getIds()){
            String[] split = bean.getIds().split(",");
            for (String id :split){
                bean.setId(Integer.parseInt(id));
                bean.setIsDelete(1);
                bean.setUpdateTime(new Date());
                int i = xdUserAiMapper.updateByPrimaryKeySelective(bean);
                xdUserAi xdUserAi = xdUserAiMapper.selectByPrimaryKey(bean.getId());
                if (1 == xdUserAi.getIsDraft()){
                    xdUserStatistics userStatistics = xdUserStatisticsMapper.selectByPrimaryKeyUserId(xdUserAi.getUserId());
                    userStatistics.setUpdateTime(new Date());
                    userStatistics.setOpusAllNumber(userStatistics.getOpusAllNumber()-1);
                    xdUserStatisticsMapper.updateByPrimaryKeySelective(userStatistics);
                }
            }
        }else{
            bean.setIsDelete(1);
            bean.setUpdateTime(new Date());
            int i = xdUserAiMapper.updateByPrimaryKeySelective(bean);
            xdUserAi xdUserAi = xdUserAiMapper.selectByPrimaryKey(bean.getId());
            if (1 == xdUserAi.getIsDraft()){
                xdUserStatistics userStatistics = xdUserStatisticsMapper.selectByPrimaryKeyUserId(xdUserAi.getUserId());
                userStatistics.setUpdateTime(new Date());
                userStatistics.setOpusAllNumber(userStatistics.getOpusAllNumber()-1);
                xdUserStatisticsMapper.updateByPrimaryKeySelective(userStatistics);
            }

        }

        return 1;
    }

    public xdWorkbenchBean opusDetails(Integer id) {
        xdWorkbenchBean bean = new xdWorkbenchBean();
        bean.setId(id);

        //工作台-主体
        xdWorkbench xdWorkbench = xdWorkbenchMapper.selectByPrimaryKey(id);
        //工作台-人像组件
        List<xdWorkbenchAi> xdWorkbenchAis = xdWorkbenchAiMapper.selectByPrimaryKeyList(id);
        //工作台-时间轴组件
        List<xdWorkbenchEditor> xdWorkbenchEditors = xdWorkbenchEditorMapper.selectByPrimaryKeyList(id);

        bean.setXdWorkbench(xdWorkbench);
        bean.setXdWorkbenchAi(xdWorkbenchAis);
        bean.setXdWorkbenchEditor(xdWorkbenchEditors);

        return bean;
    }

    public int draftUpd(xdUserStatisticsBean bean) {
        xdUserAi userAi = new xdUserAi();
        userAi.setId(bean.getId());
        userAi.setOverTime(new Date());
        userAi.setType(2);
        userAi.setIsDraft(1);
        userAi.setUpdateTime(new Date());
        userAi.setUrl(qiniuDownLoad+bean.getFileName());
        int i = xdUserAiMapper.updateByPrimaryKeySelective(userAi);

        xdUserAi xdUserAi = xdUserAiMapper.selectByPrimaryKey(bean.getId());

        xdUserStatistics userStaBean = xdUserStatisticsMapper.selectByPrimaryKeyUserId(xdUserAi.getUserId());
        if (null != userStaBean){
            xdUserStatistics userStatistics = new xdUserStatistics();
            userStatistics.setId(userStaBean.getId());
            userStatistics.setUserId(userStaBean.getUserId());
            userStatistics.setOpusAllNumber(userStaBean.getOpusAllNumber()+1);
            userStatistics.setOpusAllTime(userStaBean.getOpusAllTime()+bean.getOpusTime());
            if (bean.getOpusTime() != null){
                if (userStaBean.getUserRemainingTime() - bean.getOpusTime() <= 0){
                    userStatistics.setUserRemainingTime(0);
                }else{
                    userStatistics.setUserRemainingTime(userStaBean.getUserRemainingTime() - bean.getOpusTime());
                }
            }
            userStatistics.setUserUseTime(userStaBean.getUserUseTime()+bean.getOpusTime());
            userStatistics.setUpdateTime(new Date());
            int i1 = xdUserStatisticsMapper.updateByPrimaryKeySelective(userStatistics);

            String key = "isInformUserId"+xdUserAi.getUserId();
            boolean ifSet = redisUtils.set(key, "1");
        }
        return i;
    }

    public Integer isInform(Integer userId) {
        String key = "isInformUserId"+userId;

       // boolean ifSet = redisUtils.set(key, "1");
        Object get = redisUtils.get(key);
        redisUtils.del(key);
        if (null != get){
            return 1;
        }else{
            return null;
        }
    }

    public HashMap<String,Integer> isCreate(xdUserStatistics bean) {
        HashMap<String, Integer> map = new HashMap<>();
        xdUserStatistics userStatistics = xdUserStatisticsMapper.selectByPrimaryKeyUserId(bean.getUserId());
        if (userStatistics.getUserRemainingTime() > 0){
            map.put("isCreate",0);//正常可以生成
            if (DateTools.compare_date(userStatistics.getUserEndTime(),new Date()) == 2){
                map.put("isCreate",2);//流量包过期
            }
        }else{
            map.put("isCreate",1);//生成时长用完
            if (DateTools.compare_date(userStatistics.getUserEndTime(),new Date()) == 2){
                map.put("isCreate",2);//流量包过期
            }
        }

        return map;
    }

    public HashMap<String, String> message(xdUserStatistics bean) {
        String key = "isMessageUserId"+bean.getUserId();
        HashMap<String, String> map = new HashMap<>();
        map.put("isMessage","0");
        map.put("message","");
        xdUserStatistics userStatistics = xdUserStatisticsMapper.selectByPrimaryKeyUserId(bean.getUserId());
        Object o = redisUtils.get(key);
        if (null == o){
            if (userStatistics.getUserRemainingTime() > 10){
                String newDate = DateTools.dateToString(new Date(), "yyyy-MM-dd");
                String endDate = DateTools.dateToString(userStatistics.getUserEndTime(), "yyyy-MM-dd");
                long interval  = DateTools.subDay(newDate, endDate);
                if (interval <= 7){//小于7天就提醒快过期了
                    if (interval < 0){
                        map.put("isMessage","1");
                        map.put("message","您的创作包还有"+interval+"天就过期了。");
                    }else{
                        map.put("isMessage","1");
                        map.put("message","您的创作包已过期了。");
                    }
                }
            }else{
                //没时长了
                map.put("isMessage","1");
                map.put("message","您的创作包时长不足了，为不影响您的创作请及时充值。");
            }
            boolean ifSet = redisUtils.set(key, "1",86400);
        }

        return map;
    }

    public HashMap<String, String> sumCount() {
        HashMap<String, String> map = xdUserMapper.sumCount();
        return map;
    }
}
