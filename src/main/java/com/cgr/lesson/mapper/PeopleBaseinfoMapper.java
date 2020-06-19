package com.cgr.lesson.mapper;

import com.cgr.lesson.entity.PeopleBaseInfo;
import com.cgr.lesson.vo.req.PeopleInfoPageReqVO;
import com.cgr.lesson.vo.req.TestProReqVO;
import com.cgr.lesson.vo.resp.PeopleInfoRespVO;

import java.util.List;

public interface PeopleBaseinfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(PeopleBaseInfo record);

    int insertSelective(PeopleBaseInfo record);

    PeopleBaseInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PeopleBaseInfo record);

    int updateByPrimaryKey(PeopleBaseInfo record);

    //分页查询基本信息
    List<PeopleInfoRespVO> selectAll(PeopleInfoPageReqVO vo);

    //存储过程测试
    TestProReqVO getTestPro(TestProReqVO vo);
}