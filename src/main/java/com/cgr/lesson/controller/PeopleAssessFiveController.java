package com.cgr.lesson.controller;

import com.cgr.lesson.aop.annotation.MyLog;
import com.cgr.lesson.constants.Constant;
import com.cgr.lesson.entity.PeopleAssessFive;
import com.cgr.lesson.service.PeopleAssessFiveService;
import com.cgr.lesson.utils.DataResult;
import com.cgr.lesson.utils.JwtTokenUtil;
import com.cgr.lesson.vo.req.PeopleAssessFiveReqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 21:30 2020-06-10
 * @ Description：长者能力
 * @ Modified By：
 */

@RestController
@RequestMapping("/api")
@Api(tags = "评估表")
public class PeopleAssessFiveController {
    @Autowired
    private PeopleAssessFiveService peopleAssessFiveService;

   @PostMapping("/peopleassessfive")
   @ApiOperation(value = "长者能力新增接口")
   @MyLog(title = "评估表",action = "长者能力新增")
    public DataResult<PeopleAssessFive> peopleAssessFiveAdd(@RequestBody PeopleAssessFiveReqVO vo, HttpServletRequest request){
        String tokenStr=request.getHeader(Constant.ACCESS_TOKEN);
        String userId= JwtTokenUtil.getUserId(tokenStr);
        DataResult<PeopleAssessFive> result=DataResult.success();
        result.setData(peopleAssessFiveService.peopleAssessFiveAdd(vo,userId));
        return result;
    }

}
