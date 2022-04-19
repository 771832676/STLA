package com.stla.figure.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.stla.figure.bean.*;
import com.stla.figure.dao.*;
import com.stla.figure.deploy.ApiResult;
import com.stla.figure.utility.DateTools;
import com.stla.figure.utility.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class WebXdAiServer {

    @Autowired
    xdUserAiMapper xdUserAiMapper;

    @Autowired
    xdWorkbenchMapper xdWorkbenchMapper;

    @Autowired
    xdWorkbenchAiMapper xdWorkbenchAiMapper;

    @Autowired
    xdWorkbenchEditorMapper xdWorkbenchEditorMapper;

    @Autowired
    xdAiLogMapper xdAiLogMapper;

    @Value("${httpWebUrl}")
    private String httpWebUrl;

    public ApiResult insetOpus(xdUserAiBean bean) {
        ApiResult outBean = new ApiResult();
        Date date = new Date();
        if (null != bean.getXdUserAi() && null != bean.getXdUserAi().getId()){
            //为方便前端每次清除原数据,还原创建时间
            date = delUserWorkBench(bean.getXdUserAi().getId());
        }

        if (null != bean.getXdWorkbench()){//工作台
            xdWorkbench xdWorkbench = bean.getXdWorkbench();
            xdWorkbench.setId(null);
            int i = xdWorkbenchMapper.insertSelective(xdWorkbench);
            bean.getXdUserAi().setId(xdWorkbench.getId());
        }
        //工作台ID
        Integer settingId = bean.getXdWorkbench().getId();
        if (null != settingId){
            if (null != bean.getXdUserAi()){//作品
                bean.getXdUserAi().setWorkbench(settingId);
                bean.getXdUserAi().setElementSum(bean.getXdWorkbenchEditor().size());
                if (1 == bean.getXdUserAi().getIsDraft()){
                    bean.getXdUserAi().setStartTime(date);
                }
                bean.getXdUserAi().setCreateTime(date);
                bean.getXdUserAi().setType(1);
                int i = xdUserAiMapper.insertSelective(bean.getXdUserAi());
            }

            if (0 != bean.getXdWorkbenchAi().size()){//工作台-人像组件
                for (xdWorkbenchAi instBean: bean.getXdWorkbenchAi()) {
                    instBean.setElementId(settingId);
                    int i = xdWorkbenchAiMapper.insertSelective(instBean);
                }
            }

            if (0 != bean.getXdWorkbenchEditor().size()){//工作台-时间轴组件
                for (xdWorkbenchEditor instBean: bean.getXdWorkbenchEditor()) {
                    instBean.setElementId(settingId);
                    int i = xdWorkbenchEditorMapper.insertSelective(instBean);
                }
            }

            Integer XdUserAiId = bean.getXdUserAi().getId(); //作品id
            if (null != XdUserAiId && 1 == bean.getXdUserAi().getIsDraft()){//作品已创建并为正式作品
                //发送请求到U3D
                String data = "{\"data\" :"+JSON.toJSONString(bean)+"}";
                try {
                    String returnBean = HttpClientUtil.sendJsonStr(httpWebUrl,data);
                    JSONObject jsonObject = JSONObject.parseObject(returnBean);
                    // { "code": "200", "waitTime": "10" }
                    HashMap<String, String> map = new HashMap<>();
                    map.put("waitTime",jsonObject.get("waitTime").toString());
                    outBean.setData(map);
                    //修改状态
                    xdUserAi xdUserAiBean = new xdUserAi();
                    xdUserAiBean.setOverTime(DateTools.nextHourd(new Date(), Integer.parseInt(jsonObject.get("waitTime").toString())));//此处8分钟需要改动态
                    xdUserAiBean.setId(bean.getXdUserAi().getId());
                    xdUserAiBean.setType(1);
                    xdUserAiBean.setUpdateTime(new Date());
                    xdUserAiMapper.updateByPrimaryKeySelective(xdUserAiBean);
                    //日志
                    xdAiLog xdAiLog = new xdAiLog();
                    xdAiLog.setCreateTime(date);
                    xdAiLog.setUrl(httpWebUrl);
                    xdAiLog.setData(data);
                    xdAiLog.setReturns(jsonObject.toString());
                    xdAiLogMapper.insertSelective(xdAiLog);
                }catch (Exception e){
                    System.out.println("-----------------请求服务U3D失败----------------作品id"+XdUserAiId);
                    xdAiLog xdAiLog = new xdAiLog();
                    xdAiLog.setCreateTime(date);
                    xdAiLog.setUrl(httpWebUrl);
                    xdAiLog.setData(data);

                    xdAiLog.setReturns("Error"+e);
                    xdAiLogMapper.insertSelective(xdAiLog);
                }

            }
        }

        return outBean;
    }

    //为方便前端每次清除原数据
    public Date delUserWorkBench(Integer id){
        xdUserAi xdUserAiBean = xdUserAiMapper.selectByPrimaryKey(id);
        //工作台id
        Integer xdWorkbenchId =  xdUserAiBean.getWorkbench();
        //清除用户作品
        xdUserAiMapper.deleteByPrimaryKey(id);
        //清除工作台
        xdWorkbenchMapper.deleteByPrimaryKey(xdWorkbenchId);
        //清除工作台-人像组件
        xdWorkbenchAiMapper.deleteByPrimaryKeyEleId(xdWorkbenchId);
        //清除工作台-时间轴组件
        xdWorkbenchEditorMapper.deleteByPrimaryKeyEleId(xdWorkbenchId);

        return xdUserAiBean.getCreateTime();
    }

    public List<xdAiLog> loge() {
        List<xdAiLog> xdAiLoglist =  xdAiLogMapper.loge();
        return xdAiLoglist;
    }
}
