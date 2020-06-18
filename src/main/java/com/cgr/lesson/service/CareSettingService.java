package com.cgr.lesson.service;

import com.cgr.lesson.vo.req.CareProReqVO;
import com.cgr.lesson.vo.req.CareSettingReqVO;
import com.cgr.lesson.vo.resp.CareProResqVO;
import com.cgr.lesson.vo.resp.CareSettingResqVO;
import com.cgr.lesson.vo.resp.PageVO;

import java.util.List;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 14:55 2020-06-15
 * @ Description：
 * @ Modified By：
 */
public interface CareSettingService {
    //分页查询护理项目
    PageVO<CareProResqVO> getCareProInfo(CareProReqVO vo);

    //护理级别新增
    void CareSettingAdd(CareSettingReqVO vo,String userID);

    //查询执行计划的名称
    List<String> selectAllName();

    //根据名称获取护理级别
    CareSettingResqVO getCareSettingByName(String name);
}
