package com.cgr.lesson.mapper;

import com.cgr.lesson.entity.CareSettingDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CareSettingDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(CareSettingDetail record);

    int insertSelective(CareSettingDetail record);

    CareSettingDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CareSettingDetail record);

    int updateByPrimaryKey(CareSettingDetail record);

    //批量插入执行计划
    int batchInsert(List<CareSettingDetail> list);

    //批量删除执行计划
    int batchDelete(String pid);

    //根据名称获取执行计划
    List<CareSettingDetail> getCareSettingDetailByName(String name);

}