package com.cgr.lesson.controller;

import com.cgr.lesson.aop.annotation.MyLog;
import com.cgr.lesson.constants.Constant;
import com.cgr.lesson.entity.PeopleAssess;
import com.cgr.lesson.service.PeopleAssessService;
import com.cgr.lesson.utils.DataResult;
import com.cgr.lesson.utils.JwtTokenUtil;
import com.cgr.lesson.vo.req.PeopleAssessPageReqVO;
import com.cgr.lesson.vo.req.PeopleAssessReqVO;
import com.cgr.lesson.vo.resp.PageVO;
import com.cgr.lesson.vo.resp.PeopleAssessGradeRespVO;
import com.cgr.lesson.vo.resp.PeopleAssessRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.crypto.Data;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 14:21 2020-06-10
 * @ Description：评估表
 * @ Modified By：
 */

@RestController
@RequestMapping("/api")
@Api(tags = "评估表")
public class PeopleAssessController {
    @Autowired
    private PeopleAssessService peopleAssessService;

    @PostMapping("/peopleassess")
    @ApiOperation(value = "评估表新增接口")
    @MyLog(title = "评估表",action = "评估表新增")
    public DataResult<PeopleAssess> peopleAssessAdd(@RequestBody @Valid PeopleAssessReqVO vo, HttpServletRequest request){
        String tokenStr=request.getHeader(Constant.ACCESS_TOKEN);
        String userId= JwtTokenUtil.getUserId(tokenStr);
        DataResult<PeopleAssess> result=DataResult.success();
        result.setData(peopleAssessService.peopleAssessAdd(vo,userId));
        return result;
    }

    @GetMapping("/peopleassess/{assessID}")
    @ApiOperation(value = "根据评估编号获取前4个评估等级接口")
    @MyLog(title = "评估表",action = "根据评估编号获取前4个评估等级")
    public DataResult<PeopleAssessGradeRespVO> getGradeByAssessId(@PathVariable("assessID") String assessID){
        DataResult<PeopleAssessGradeRespVO> result=DataResult.success();
        result.setData(peopleAssessService.getGradeById(assessID));
        return result;
    }

    @PostMapping("/peopleassesss")
    @ApiOperation(value = "分页获取评估信息列表接口")
    @MyLog(title = "评估表",action = "分页获取评估信息列表")
    public DataResult<PageVO<PeopleAssessRespVO>> pageInfo(@RequestBody PeopleAssessPageReqVO vo){
        DataResult<PageVO<PeopleAssessRespVO>> result=DataResult.success();
        result.setData(peopleAssessService.pageInfo(vo));
        return result;
    }
}
