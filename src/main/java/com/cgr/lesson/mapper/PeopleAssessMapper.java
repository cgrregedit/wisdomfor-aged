package com.cgr.lesson.mapper;

import com.cgr.lesson.entity.PeopleAssess;
import com.cgr.lesson.vo.req.PeopleAssessPageReqVO;
import com.cgr.lesson.vo.resp.PeopleAssessGradeRespVO;
import com.cgr.lesson.vo.resp.PeopleAssessRespVO;

import java.util.List;

public interface PeopleAssessMapper {
    int deleteByPrimaryKey(String id);

    int insert(PeopleAssess record);

    int insertSelective(PeopleAssess record);

    PeopleAssess selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PeopleAssess record);

    int updateByPrimaryKey(PeopleAssess record);

    //根据评估编号获取前4个评估等级
    PeopleAssessGradeRespVO getGradeByAssessId(String assessId);

    //分页获取评估表信息
    List<PeopleAssessRespVO> pageInfo(PeopleAssessPageReqVO vo);
}