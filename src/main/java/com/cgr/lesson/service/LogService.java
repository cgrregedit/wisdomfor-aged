package com.cgr.lesson.service;

import com.cgr.lesson.entity.SysLog;
import com.cgr.lesson.vo.req.SysLogPageReqVO;
import com.cgr.lesson.vo.resp.PageVO;

import java.util.List;


/**
* @Description:  操作日志
* @Param:
* @return:
* @Author:cgr
* @Date: 2020-05-23
*/
public interface LogService {

    PageVO<SysLog> pageInfo(SysLogPageReqVO vo);

    void deletedLog(List<String> logIds);
}
