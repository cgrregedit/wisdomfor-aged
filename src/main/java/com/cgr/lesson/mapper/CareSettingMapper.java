package com.cgr.lesson.mapper;

import com.cgr.lesson.entity.CareSetting;
import com.cgr.lesson.vo.req.CareProReqVO;
import com.cgr.lesson.vo.resp.CareProResqVO;

import java.util.List;

public interface CareSettingMapper {
    int deleteByPrimaryKey(String id);

    int insert(CareSetting record);

    int insertSelective(CareSetting record);

    CareSetting selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CareSetting record);

    int updateByPrimaryKey(CareSetting record);

    //根据查询条件查询护理项目
    List<CareProResqVO> selectByFind(CareProReqVO vo);

    //查询执行计划的名称
    List<String> selectAllName();

    //根据名称查询护理级别
    CareSetting getCareSettingByName(String name);
}