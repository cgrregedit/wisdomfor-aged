package com.cgr.lesson.service;

import com.cgr.lesson.entity.PeopleAssess;
import com.cgr.lesson.vo.req.PeopleAssessPageReqVO;
import com.cgr.lesson.vo.req.PeopleAssessReqVO;
import com.cgr.lesson.vo.resp.PageVO;
import com.cgr.lesson.vo.resp.PeopleAssessGradeRespVO;
import com.cgr.lesson.vo.resp.PeopleAssessRespVO;

import java.util.List;

public interface PeopleAssessService {
    //评估表新增
    PeopleAssess peopleAssessAdd(PeopleAssessReqVO vo,String userID);

    //根据评估编号获取前4个评估等级
    PeopleAssessGradeRespVO getGradeById(String assessId);

    //分页获取评估表信息
    PageVO<PeopleAssessRespVO> pageInfo(PeopleAssessPageReqVO vo);
}
