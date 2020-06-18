package com.cgr.lesson.controller;

import com.cgr.lesson.aop.annotation.MyLog;
import com.cgr.lesson.constants.Constant;
import com.cgr.lesson.entity.PeopleAssessSecond;
import com.cgr.lesson.service.PeopleAssessSecondService;
import com.cgr.lesson.utils.DataResult;
import com.cgr.lesson.utils.JwtTokenUtil;
import com.cgr.lesson.vo.req.PeopleAssessSecondAddReqVO;
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
 * @ Date       ：Created in 22:17 2020-06-01
 * @ Description： 精神状态评估表
 * @ Modified By：
 */
@RestController
@RequestMapping("/api")
@Api(tags = "评估表")
public class PeopleAssessSecondController {
    @Autowired
    private PeopleAssessSecondService peopleAssessSecondService;

    @PostMapping("/peopleassesssecond")
    @ApiOperation("精神状态评估表新增接口")
    @MyLog(title = "评估表",action = "精神状态评估表新增")
    public DataResult<PeopleAssessSecond> peopleAssessSecondAdd(@RequestBody @Valid PeopleAssessSecondAddReqVO vo, HttpServletRequest request){
        String tokenStr=request.getHeader(Constant.ACCESS_TOKEN);
        String userId= JwtTokenUtil.getUserId(tokenStr);
        DataResult<PeopleAssessSecond> result=DataResult.success();
        result.setData(peopleAssessSecondService.peopleAssessSecondAdd(vo,userId));
        return result;
    }
}
