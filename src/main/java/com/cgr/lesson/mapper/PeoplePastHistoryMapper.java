package com.cgr.lesson.mapper;

import com.cgr.lesson.entity.PeoplePastHistory;

public interface PeoplePastHistoryMapper {
    int deleteByPrimaryKey(String id);

    int insert(PeoplePastHistory record);

    int insertSelective(PeoplePastHistory record);

    PeoplePastHistory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PeoplePastHistory record);

    int updateByPrimaryKey(PeoplePastHistory record);

    //根据pid获取既往史
    PeoplePastHistory getPastHistoryByPid(String pid);
}