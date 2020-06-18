package com.cgr.lesson.controller;

import com.cgr.lesson.aop.annotation.MyLog;
import com.cgr.lesson.constants.Constant;
import com.cgr.lesson.entity.PeopleAssessFour;
import com.cgr.lesson.service.PeopleAssessFourService;
import com.cgr.lesson.utils.DataResult;
import com.cgr.lesson.utils.JwtTokenUtil;
import com.cgr.lesson.vo.req.PeopleAssessFourAddReqVO;
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
 * @ Date       ：Created in 13:12 2020-06-09
 * @ Description：
 * @ Modified By：
 */

@RestController
@RequestMapping("/api")
@Api(tags = "评估表")
public class PeopleAssessFourController {
    @Autowired
    private PeopleAssessFourService peopleAssessFourService;

    @PostMapping("/peopleassessfour")
    @ApiOperation("社会参与评估表新增接口")
    @MyLog(title = "评估表",action = "社会参与评估表新增")
    public DataResult<PeopleAssessFour> peopleAssessFourAdd(@RequestBody @Valid PeopleAssessFourAddReqVO vo, HttpServletRequest request){
       String tokenStr=request.getHeader(Constant.ACCESS_TOKEN);
       String usrId= JwtTokenUtil.getUserId(tokenStr);
       DataResult<PeopleAssessFour> dataResult=DataResult.success();
       dataResult.setData(peopleAssessFourService.peopleAssessFourAdd(vo,usrId));
       return dataResult;
    }
}
