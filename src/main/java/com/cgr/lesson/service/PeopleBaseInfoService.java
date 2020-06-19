package com.cgr.lesson.service;

import com.cgr.lesson.entity.PeopleBaseInfo;
import com.cgr.lesson.vo.req.PeopleBaseInfoReqVO;
import com.cgr.lesson.vo.req.PeopleBaseInfoUpdateReqVO;
import com.cgr.lesson.vo.req.PeopleInfoPageReqVO;
import com.cgr.lesson.vo.req.TestProReqVO;
import com.cgr.lesson.vo.resp.PageVO;
import com.cgr.lesson.vo.resp.PeopleInfoRespVO;

public interface PeopleBaseInfoService {
    //基本信息新增
    PeopleBaseInfo peopleBaseInfoAdd(PeopleBaseInfoReqVO vo,String userId);
    //基本信息编辑
    void peopleBaseInfoUpdate(PeopleBaseInfoUpdateReqVO vo,String userId);
    //分页查询个人基本信息
     PageVO<PeopleInfoRespVO> pageInfo(PeopleInfoPageReqVO vo);
     //根据id获取基本信息
    PeopleBaseInfo getPeopleBaseInfoById(String id);
    //存储过程测试
    TestProReqVO getTestPro(TestProReqVO vo);

}
