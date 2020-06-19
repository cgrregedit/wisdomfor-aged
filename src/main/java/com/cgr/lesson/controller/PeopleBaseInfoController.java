package com.cgr.lesson.controller;

import com.cgr.lesson.aop.annotation.MyLog;
import com.cgr.lesson.constants.Constant;
import com.cgr.lesson.entity.PeopleBaseInfo;
import com.cgr.lesson.service.PeopleBaseInfoService;
import com.cgr.lesson.utils.DataResult;
import com.cgr.lesson.utils.JwtTokenUtil;
import com.cgr.lesson.vo.req.PeopleBaseInfoReqVO;
import com.cgr.lesson.vo.req.PeopleBaseInfoUpdateReqVO;
import com.cgr.lesson.vo.req.PeopleInfoPageReqVO;
import com.cgr.lesson.vo.req.TestProReqVO;
import com.cgr.lesson.vo.resp.PageVO;
import com.cgr.lesson.vo.resp.PeopleInfoRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 20:09 2020-05-26
 * @ Description：
 * @ Modified By：
 */

@RestController
@RequestMapping("/api")
@Api(tags = "老人建档管理-信息登记")
public class PeopleBaseInfoController {
    @Autowired
    private PeopleBaseInfoService peopleBaseInfoService;

    @PostMapping("/peoplebaseinfo")
    @ApiOperation(value = "基本信息新增接口")
    @MyLog(title = "老人建档管理-信息登记",action ="新增信息登记-基本信息接口" )
    public DataResult<PeopleBaseInfo> addPeopleBaseInfo(@RequestBody @Valid PeopleBaseInfoReqVO vo, HttpServletRequest httpServletRequest){
        String userId= JwtTokenUtil.getUserId(httpServletRequest.getHeader(Constant.ACCESS_TOKEN));
        DataResult<PeopleBaseInfo> dataResult=DataResult.success();
        dataResult.setData(peopleBaseInfoService.peopleBaseInfoAdd(vo,userId));
        return dataResult;
    }

    @PutMapping("/peoplebaseinfo")
    @ApiOperation(value = "编辑基本信息接口")
    @MyLog(title = "老人建档管理-信息登记",action = "编辑信息登记-基本信息接口")
    public DataResult updatePeopleBaseInfo(@RequestBody @Valid PeopleBaseInfoUpdateReqVO vo, HttpServletRequest httpServletRequest){
        String userId=JwtTokenUtil.getUserId(httpServletRequest.getHeader(Constant.ACCESS_TOKEN));
        peopleBaseInfoService.peopleBaseInfoUpdate(vo,userId);
        return DataResult.success();
    }

    @PostMapping("/peoplebaseinfos")
    @ApiOperation(value = "分页获取基本信息接口")
    @MyLog(title = "老人建档管理-信息登记",action = "分页获取信息登记-基本信息接口")
    public DataResult<PageVO<PeopleInfoRespVO>> pageInfo(@RequestBody PeopleInfoPageReqVO vo){
        DataResult<PageVO<PeopleInfoRespVO>> result=DataResult.success();
        result.setData(peopleBaseInfoService.pageInfo(vo));
        return  result;
    }

    @GetMapping("/peoplebaseinfo/{id}")
    @ApiOperation(value = "根据ID获取患者基本信息")
    @MyLog(title = "老人建档管理-信息登记",action = "根据ID获取个人基本信息-基本信息接口")
    public DataResult<PeopleBaseInfo> getPeopleBaseInfoById(@PathVariable("id") String id){
        DataResult<PeopleBaseInfo> result=DataResult.success();
        result.setData(peopleBaseInfoService.getPeopleBaseInfoById(id));
        return  result;
    }

    @PostMapping("/testpro")
    @ApiOperation(value = "存储过程测试")
    public DataResult<TestProReqVO> getTestPro(){
        DataResult<TestProReqVO> result=DataResult.success();
        TestProReqVO testProReqVO=new TestProReqVO();
        testProReqVO.setOrganid("110");
        testProReqVO.setOnlynumber("123456");
        testProReqVO.setVisitnum(1);
        result.setData(peopleBaseInfoService.getTestPro(testProReqVO));
        return result;
    }
}
