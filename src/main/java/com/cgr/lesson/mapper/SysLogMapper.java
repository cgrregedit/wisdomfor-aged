package com.cgr.lesson.mapper;

import com.cgr.lesson.entity.SysLog;
import com.cgr.lesson.vo.req.SysLogPageReqVO;

import java.util.List;

public interface SysLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);

    //查询日志
    List<SysLog> selectAll(SysLogPageReqVO vo);

    //批量删除日志
    int batchDeletedLog(List<String> logIds);
}