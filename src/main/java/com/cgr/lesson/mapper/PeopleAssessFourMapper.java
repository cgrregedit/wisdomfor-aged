package com.cgr.lesson.mapper;

import com.cgr.lesson.entity.PeopleAssessFour;

public interface PeopleAssessFourMapper {
    int deleteByPrimaryKey(String id);

    int insert(PeopleAssessFour record);

    int insertSelective(PeopleAssessFour record);

    PeopleAssessFour selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PeopleAssessFour record);

    int updateByPrimaryKey(PeopleAssessFour record);
}