package com.stla.figure.server;

import com.stla.figure.bean.examCompetition;
import com.stla.figure.bean.examCompetitionRest;
import com.stla.figure.dao.examCompetitionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamCompetitionServer {

    @Autowired
    examCompetitionMapper ExamCompetitionMapper;

    public List<examCompetition> getExamCompetitionList(examCompetitionRest bean){

        List<examCompetition> getExamCompetitionList = ExamCompetitionMapper.getExamCompetitionList(bean);

        return getExamCompetitionList;
    }

    public examCompetition selectByPrimaryKey(Integer id){

        examCompetition selectByPrimaryKey = ExamCompetitionMapper.selectByPrimaryKey(id);

        return selectByPrimaryKey;
    }


    public List<examCompetition> getQuestionBank(examCompetitionRest bean) {

        List<examCompetition> getQuestionBank = ExamCompetitionMapper.getQuestionBank(bean);

        return getQuestionBank;
    }
}
