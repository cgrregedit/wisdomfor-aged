package com.cgr.lesson.controller;

import com.cgr.lesson.aop.annotation.MyLog;
import com.cgr.lesson.constants.Constant;
import com.cgr.lesson.entity.PeoplePackage;
import com.cgr.lesson.service.PeoplePackageService;
import com.cgr.lesson.utils.DataResult;
import com.cgr.lesson.utils.JwtTokenUtil;
import com.cgr.lesson.vo.req.PeoplePackageAddReqVO;
import com.cgr.lesson.vo.req.PeoplePackagePageReqVO;
import com.cgr.lesson.vo.resp.PageVO;
import com.cgr.lesson.vo.resp.PeoplePackageResqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 15:02 2020-06-16
 * @ Description：套餐生成
 * @ Modified By：
 */

@RestController
@RequestMapping("/api")
@Api(tags = "个人套餐生成")
public class PeoplePackageController {
    @Autowired
    private PeoplePackageService peoplePackageService;

    @PostMapping("/peoplepackages")
    @ApiOperation(value = "分页查询个人套餐生成接口")
    @MyLog(title = "个人套餐生成",action = "分页查询个人套餐生成信息")
    public DataResult<PageVO<PeoplePackageResqVO>> pageInfo(@RequestBody PeoplePackagePageReqVO vo){
        DataResult<PageVO<PeoplePackageResqVO>> result=DataResult.success();
        result.setData(peoplePackageService.selectAllPackage(vo));
        return result;
    }

    @PostMapping("/peoplepackage")
    @ApiOperation(value = "套餐选择新增接口")
    @MyLog(title = "个人套餐生成",action = "套餐选择新增")
    public DataResult<PeoplePackage> peoplePackageAdd(@RequestBody @Valid PeoplePackageAddReqVO vo, HttpServletRequest request){
        String tokenStr=request.getHeader(Constant.ACCESS_TOKEN);
        String userID= JwtTokenUtil.getUserId(tokenStr);
        DataResult<PeoplePackage> result=DataResult.success();
        result.setData(peoplePackageService.peoplePackageAdd(vo,userID));
        return result;
    }
}
