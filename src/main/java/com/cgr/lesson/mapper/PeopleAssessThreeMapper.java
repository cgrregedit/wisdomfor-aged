package com.cgr.lesson.mapper;

import com.cgr.lesson.entity.PeopleAssessThree;

public interface PeopleAssessThreeMapper {
    int deleteByPrimaryKey(String id);

    int insert(PeopleAssessThree record);

    int insertSelective(PeopleAssessThree record);

    PeopleAssessThree selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PeopleAssessThree record);

    int updateByPrimaryKey(PeopleAssessThree record);
}