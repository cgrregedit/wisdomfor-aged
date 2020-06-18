package com.cgr.lesson.controller;

import com.cgr.lesson.aop.annotation.MyLog;
import com.cgr.lesson.constants.Constant;
import com.cgr.lesson.entity.PeopleAssessThree;
import com.cgr.lesson.service.PeopleAssessThreeService;
import com.cgr.lesson.utils.DataResult;
import com.cgr.lesson.utils.JwtTokenUtil;
import com.cgr.lesson.vo.req.PeopleAssessThreeAddReqVO;
import io.swagger.annotations.Api;
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
 * @ Date       ：Created in 10:58 2020-06-02
 * @ Description：感知觉与沟通评估表
 * @ Modified By：
 */

@RestController
@RequestMapping("/api")
@Api(tags = "评估表")
public class PeopleAssessThreeController {
    @Autowired
    private PeopleAssessThreeService peopleAssessThreeService;

    @PostMapping("/peopleAssessThree")
    @ApiOperation("感知觉与沟通评估表新增接口")
    @MyLog(title = "评估表",action = "感知觉与沟通评估表新增")
    public DataResult<PeopleAssessThree> peopleAssessThreeAdd(@RequestBody @Valid PeopleAssessThreeAddReqVO vo, HttpServletRequest request){
        String tokenStr=request.getHeader(Constant.ACCESS_TOKEN);
        String userId= JwtTokenUtil.getUserId(tokenStr);
        DataResult<PeopleAssessThree> result=DataResult.success();
        result.setData(peopleAssessThreeService.peopleAssessThreeAdd(vo,userId));
        return  result;
    }
}
