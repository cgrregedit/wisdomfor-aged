package com.cgr.lesson.controller;


import com.cgr.lesson.aop.annotation.MyLog;
import com.cgr.lesson.constants.Constant;
import com.cgr.lesson.entity.PeopleAssessFirst;
import com.cgr.lesson.service.PeopleAssessFirstService;
import com.cgr.lesson.utils.DataResult;
import com.cgr.lesson.utils.JwtTokenUtil;
import com.cgr.lesson.vo.req.PeopleAssessFirstAddReqVO;
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
 * @ Date       ：Created in 14:57 2020-05-30
 * @ Description：入院评估一
 * @ Modified By：
 */

@RestController
@RequestMapping("/api")
@Api(tags = "评估表")
public class PeopleAssessFirstController {
    @Autowired
    private PeopleAssessFirstService peopleAssessFirstService;

    @PostMapping("/peopleassessfirst")
    @ApiOperation("日常生活活动评估新增接口")
    @MyLog(title = "评估表",action = "日常生活活动评估新增")
    public DataResult<PeopleAssessFirst> peopleAssessFirstAdd(@RequestBody @Valid PeopleAssessFirstAddReqVO vo, HttpServletRequest request){
        String tokenStr=request.getHeader(Constant.ACCESS_TOKEN);
        String userId= JwtTokenUtil.getUserId(tokenStr);
        DataResult<PeopleAssessFirst> result=DataResult.success();
        result.setData(peopleAssessFirstService.peopleAssessFirstAdd(vo,userId));
        return  result;
    }
}
