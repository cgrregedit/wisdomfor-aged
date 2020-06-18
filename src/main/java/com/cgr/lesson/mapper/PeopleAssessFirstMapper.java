package com.cgr.lesson.mapper;

import com.cgr.lesson.entity.PeopleAssessFirst;

public interface PeopleAssessFirstMapper {
    int deleteByPrimaryKey(String id);

    int insert(PeopleAssessFirst record);

    int insertSelective(PeopleAssessFirst record);

    PeopleAssessFirst selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PeopleAssessFirst record);

    int updateByPrimaryKey(PeopleAssessFirst record);
}