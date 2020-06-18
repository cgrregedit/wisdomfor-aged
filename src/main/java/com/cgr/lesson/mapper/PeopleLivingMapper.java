package com.cgr.lesson.mapper;

import com.cgr.lesson.entity.PeopleLiving;

public interface PeopleLivingMapper {
    int deleteByPrimaryKey(String id);

    int insert(PeopleLiving record);

    int insertSelective(PeopleLiving record);

    PeopleLiving selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PeopleLiving record);

    int updateByPrimaryKey(PeopleLiving record);

    //根据pid获取生活环境
    PeopleLiving getLivingByPid(String pid);
}