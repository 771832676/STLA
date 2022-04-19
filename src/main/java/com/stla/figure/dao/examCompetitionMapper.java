package com.stla.figure.dao;

import com.stla.figure.bean.examCompetition;
import com.stla.figure.bean.examCompetitionRest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface examCompetitionMapper {
    int deleteByPrimaryKey(Byte id);

    int insert(examCompetition record);

    int insertSelective(examCompetition record);

    examCompetition selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(examCompetition record);

    int updateByPrimaryKey(examCompetition record);

    List<examCompetition> getExamCompetitionList(examCompetitionRest bean);

    List<examCompetition> getQuestionBank(examCompetitionRest bean);
}