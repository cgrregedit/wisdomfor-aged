package com.cgr.lesson.controller;

import com.cgr.lesson.aop.annotation.MyLog;
import com.cgr.lesson.entity.SysDict;
import com.cgr.lesson.service.DictService;
import com.cgr.lesson.utils.DataResult;
import com.cgr.lesson.vo.req.DictPageReqVO;
import com.cgr.lesson.vo.resp.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 20:54 2020-05-25
 * @ Description：
 * @ Modified By：
 */

@RestController
@RequestMapping("/api")
@Api(tags = "系统管理-数字字典")
public class DictController {
    @Autowired
    private DictService dictService;

    @GetMapping("/dict/{typeid}")
    @ApiOperation(value = "根据typeid获取字典")
    @MyLog(title = "系统管理-数字字典",action = "根据typeid获取字典")
    public DataResult<List<Map<String,String>>> getDictBlood(@PathVariable("typeid") Integer typeid){
        DataResult<List<Map<String,String>>> dataResult=DataResult.success();
        dataResult.setData(dictService.selectDict(typeid));
        return dataResult;
    }

    @PostMapping("/dicts")
    @ApiOperation(value = "分页获取字典")
    @MyLog(title = "系统管理-数字字典",action = "分页获取字典")
    public DataResult<PageVO<SysDict>> selectAll(@RequestBody DictPageReqVO vo){
        DataResult<PageVO<SysDict>> result=DataResult.success();
        result.setData(dictService.selectAll(vo));
        return result;
    }
}
