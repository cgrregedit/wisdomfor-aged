package com.cgr.lesson.controller;

import com.cgr.lesson.aop.annotation.MyLog;
import com.cgr.lesson.entity.SysLog;
import com.cgr.lesson.service.LogService;
import com.cgr.lesson.utils.DataResult;
import com.cgr.lesson.vo.req.SysLogPageReqVO;
import com.cgr.lesson.vo.resp.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
* @Description:  操作日志
* @Param:
* @return:
* @Author:cgr
* @Date: 2020-05-23
*/
@RestController
@RequestMapping("/api")
@Api(tags = "系统管理-日志管理")
public class LogController {
    @Autowired
    private LogService logService;

    @PostMapping("/logs")
    @ApiOperation(value = "分页查找操作日志接口")
    public DataResult<PageVO<SysLog>> pageInfo(@RequestBody SysLogPageReqVO vo){
        PageVO<SysLog> sysLogPageVO = logService.pageInfo(vo);
        DataResult result=DataResult.success();
        result.setData(sysLogPageVO);
        return result;
    }


    @DeleteMapping("/log")
    @ApiOperation(value = "删除日志接口")
    @MyLog(title = "系统管理-日志管理",action = "删除日志接口")
    public DataResult deletedLog(@RequestBody @ApiParam(value = "日志id集合") List<String> logIds){
        logService.deletedLog(logIds);
        DataResult result=DataResult.success();
        return result;
    }
}
