package com.cgr.lesson.controller;

import com.cgr.lesson.aop.annotation.MyLog;
import com.cgr.lesson.constants.Constant;
import com.cgr.lesson.service.CareSettingService;
import com.cgr.lesson.utils.DataResult;
import com.cgr.lesson.utils.JwtTokenUtil;
import com.cgr.lesson.vo.req.CareProReqVO;
import com.cgr.lesson.vo.req.CareSettingReqVO;
import com.cgr.lesson.vo.resp.CareProResqVO;
import com.cgr.lesson.vo.resp.CareSettingResqVO;
import com.cgr.lesson.vo.resp.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 15:16 2020-06-15
 * @ Description：护理级别设置
 * @ Modified By：
 */

@RestController
@RequestMapping("/api")
@Api(tags = "护理级别设置")
public class CareSettingController {
    @Autowired
    private CareSettingService careSettingService;

    @PostMapping("/carepros")
    @ApiOperation(value ="查询护理项目接口")
    @MyLog(title = "护理级别设置",action = "查询护理项目")
    public DataResult<PageVO<CareProResqVO>> getCareProInfo(@RequestBody CareProReqVO vo){
       DataResult<PageVO<CareProResqVO>> result=DataResult.success();
       result.setData(careSettingService.getCareProInfo(vo));
       return result;
    }

    @PostMapping("/caresetting")
    @ApiOperation(value = "护理级别设置新增接口")
    @MyLog(title = "护理级别设置",action = "护理级别设置新增")
    public DataResult CareSettingAdd(@RequestBody @Valid CareSettingReqVO vo, HttpServletRequest request){
        String tokenStr=request.getHeader(Constant.ACCESS_TOKEN);
        String userId= JwtTokenUtil.getUserId(tokenStr);
        careSettingService.CareSettingAdd(vo,userId);
        return DataResult.success();
    }

    @GetMapping("/caresettings")
    @ApiOperation(value = "获取所有护理级别名称接口")
    @MyLog(title = "护理级别设置",action = "获取所有护理级别名称")
    public DataResult<List<String>> selectAllName(HttpServletRequest request){
        DataResult<List<String>> result=DataResult.success();
        result.setData(careSettingService.selectAllName());
        return result;
    }

    @GetMapping("/caresetting/{name}")
    @ApiOperation(value = "根据名称获取护理级别接口")
    @MyLog(title = "护理级别设置",action = "根据名称获取护理级别")
    public DataResult<CareSettingResqVO> selectCareSettingByName(@PathVariable("name") String name){
        DataResult<CareSettingResqVO> result=DataResult.success();
        result.setData(careSettingService.getCareSettingByName(name));
        return result;
    }

}
