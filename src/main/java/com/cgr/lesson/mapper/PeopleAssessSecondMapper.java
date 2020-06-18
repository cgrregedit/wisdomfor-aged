package com.cgr.lesson.mapper;

import com.cgr.lesson.entity.PeopleAssessSecond;

public interface PeopleAssessSecondMapper {
    int deleteByPrimaryKey(String id);

    int insert(PeopleAssessSecond record);

    int insertSelective(PeopleAssessSecond record);

    PeopleAssessSecond selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PeopleAssessSecond record);

    int updateByPrimaryKey(PeopleAssessSecond record);
}