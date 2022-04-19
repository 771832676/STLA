package com.stla.figure.controller;

import com.stla.figure.bean.examCompetition;
import com.stla.figure.bean.examCompetitionRest;
import com.stla.figure.deploy.ApiResult;
import com.stla.figure.server.ExamCompetitionServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamCompetitionController {

    @Autowired
    ExamCompetitionServer examCompetitionServer;

    //填空题接口
    /*1.填空题下标0为题目规则介绍
    2.从1开始为题目内容,知识库题目内容含请x号选手作答
    3.返回内容含题目总量和当前题位和剩余题量*/
    @RequestMapping("/getTkexam")
    public ApiResult getTkexam(@RequestBody examCompetitionRest bean){
        bean.setTitle("第一轮");
        List<examCompetition> getTkexamlist = examCompetitionServer.getExamCompetitionList(bean);
        return  ApiResult.success(getTkexamlist);
    }

    //判断题接口
    /*1.判断题下标0为题目规则介绍
    2.从1开始为题目内容,知识库题目含座位号作答
    3.返回内容含题目总量和当前题位和剩余题量*/
    @RequestMapping("/getPdexam")
    public ApiResult getPdexam(@RequestBody examCompetitionRest bean){
        bean.setTitle("第二轮");
        List<examCompetition> getPdexam = examCompetitionServer.getExamCompetitionList(bean);
        return  ApiResult.success(getPdexam);
    }

    //抢答题接口
    /*1.抢答题下标0为题目规则介绍
    2.从1开始为题目内容,知识库题目含座位号作答
    3.返回内容含题目总量和当前题位和剩余题量*/
    @RequestMapping("/getQdexam")
    public ApiResult getQdexam(@RequestBody examCompetitionRest bean){
        bean.setTitle("第三轮");
        List<examCompetition> getQdexam = examCompetitionServer.getExamCompetitionList(bean);
        return  ApiResult.success(getQdexam);
    }

    //简答题题接口
    /*1.简答题下标0为规则介绍
    2.接口有10,20,30请求参数对应返回题目数据同时含此类题当前位置和总量   */
    @RequestMapping("/getJdexam")
    public ApiResult getJdexam(@RequestBody examCompetitionRest bean){
        bean.setTitle("第四轮");
        List<examCompetition> getJdexam = examCompetitionServer.getExamCompetitionList(bean);

        List<examCompetition>  tenList = new ArrayList<>();
        List<examCompetition>  twentyList = new ArrayList<>();
        List<examCompetition>  thirtyList = new ArrayList<>();
        List<examCompetition>  zeroList = new ArrayList<>();

        for (examCompetition questionBankBean: getJdexam) {
            if (10 == questionBankBean.getScore()){
                tenList.add(questionBankBean);
            }if (20 == questionBankBean.getScore()){
                twentyList.add(questionBankBean);
            }if (30 == questionBankBean.getScore()){
                thirtyList.add(questionBankBean);
            }if (0 ==  questionBankBean.getScore()){
                zeroList.add(questionBankBean);
            }

        }

        HashMap<String, List> map = new HashMap<>();
        map.put("zeroList",zeroList);
        map.put("tenList",tenList);
        map.put("twentyList",twentyList);
        map.put("thirtyList",thirtyList);


        return  ApiResult.success(map);
    }

    //混合题(一站到底)题接口
    /*1.混合题下标0为题目规则介绍
    2.从1开始为题目内容,知识库题目含座位号作答
    3.返回内容含题目总量和当前题位和剩余题量*/
    @RequestMapping("/getHhexam")
    public ApiResult getHhexam(@RequestBody examCompetitionRest bean){
        bean.setTitle("第五轮");
        List<examCompetition> getHhexam = examCompetitionServer.getExamCompetitionList(bean);
        return  ApiResult.success(getHhexam);
    }

    //题库接口
    /*1.返回四种类型题库,其中简答题多加一层分值分类下标1开始
    2.题库只返回题目*/
    @RequestMapping("/getQuestionBankList")
    public ApiResult getQuestionBank(@RequestBody  examCompetitionRest bean){
        List<examCompetition> getQuestionBankList = examCompetitionServer.getQuestionBank(bean);

        List<examCompetition>  oneList = new ArrayList<>();
        List<examCompetition>  twoList = new ArrayList<>();
        List<examCompetition>  threeList = new ArrayList<>();
        //List<examCompetition>  fourList = new ArrayList<>();
        List<examCompetition>  fiveList = new ArrayList<>();

        List<examCompetition>  tenList = new ArrayList<>();
        List<examCompetition>  twentyList = new ArrayList<>();
        List<examCompetition>  thirtyList = new ArrayList<>();

        for (examCompetition questionBankBean: getQuestionBankList) {
            if ("第一轮".equals(questionBankBean.getTitle())){
                oneList.add(questionBankBean);
            }else if ("第二轮".equals(questionBankBean.getTitle())){
                twoList.add(questionBankBean);
            }else if ("第三轮".equals(questionBankBean.getTitle())){
                threeList.add(questionBankBean);
            }else if ("第四轮".equals(questionBankBean.getTitle())){
                if (10 == questionBankBean.getScore()){
                    tenList.add(questionBankBean);
                }if (20 == questionBankBean.getScore()){
                    twentyList.add(questionBankBean);
                }if (30 == questionBankBean.getScore()){
                    thirtyList.add(questionBankBean);
                }
            }else if ("第五轮".equals(questionBankBean.getTitle())){
                fiveList.add(questionBankBean);
            }
        }
        HashMap<String, List> fiveBean = new HashMap<>();
        fiveBean.put("tenList",tenList);
        fiveBean.put("twentyList",twentyList);
        fiveBean.put("thirtyList",thirtyList);

        HashMap<String, Object> map = new HashMap<>();
        map.put("oneList",oneList);
        map.put("twoList",twoList);
        map.put("threeList",threeList);
        map.put("fourList",fiveBean);
        map.put("fiveList",fiveList);

        return  ApiResult.success(map);
    }

    //跳题接口
    /*1.请求时携带题目类型和对应下标(下标1开始)接口返回对应试题*/
    @RequestMapping("/getJumpOver")
    public ApiResult getJumpOver(@RequestBody  examCompetitionRest bean){
        examCompetition examCompetition = examCompetitionServer.selectByPrimaryKey(bean.getId());
        return  ApiResult.success(examCompetition);
    }


}
