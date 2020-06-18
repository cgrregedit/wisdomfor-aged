package com.cgr.lesson.mapper;

import com.cgr.lesson.entity.PeopleAssessFive;

public interface PeopleAssessFiveMapper {
    int deleteByPrimaryKey(String id);

    int insert(PeopleAssessFive record);

    int insertSelective(PeopleAssessFive record);

    PeopleAssessFive selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PeopleAssessFive record);

    int updateByPrimaryKey(PeopleAssessFive record);
}