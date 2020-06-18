package com.cgr.lesson.controller;

import com.cgr.lesson.aop.annotation.MyLog;
import com.cgr.lesson.constants.Constant;
import com.cgr.lesson.entity.PeoplePastHistory;
import com.cgr.lesson.service.PeoplePastHistoryService;
import com.cgr.lesson.utils.DataResult;
import com.cgr.lesson.utils.JwtTokenUtil;
import com.cgr.lesson.vo.req.PeoplePastHistoryAddReqVO;
import com.cgr.lesson.vo.req.PeoplePastHistoryUpdateReqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 21:51 2020-05-27
 * @ Description：既往史
 * @ Modified By：
 */

@RestController
@RequestMapping("/api")
@Api(tags = "老人建档管理-信息登记")
public class PeoplePastHistoryController {
    @Autowired
    private PeoplePastHistoryService peoplePastHistoryService;

    @PostMapping("/peoplepasthistory")
    @ApiOperation(value = "既往史新增接口")
    @MyLog(title = "老人建档管理-信息登记",action ="新增信息登记-既往史新增接口")
    public DataResult<PeoplePastHistory> peoplePastHistoryAdd(@RequestBody @Valid PeoplePastHistoryAddReqVO vo, HttpServletRequest request){
        String tokenStr=request.getHeader(Constant.ACCESS_TOKEN);
        String userId= JwtTokenUtil.getUserId(tokenStr);
        DataResult<PeoplePastHistory> result=DataResult.success();
        result.setData(peoplePastHistoryService.PeoplePastHistoryAdd(vo,userId));
        return result;
    }

    @PutMapping("/peoplepasthistory")
    @ApiOperation(value = "既往史编辑接口")
    @MyLog(title = "老人建档管理-信息登记",action = "新增信息登记-既往史编辑接口")
    public DataResult peoplePastHistoryUpdate(@RequestBody @Valid PeoplePastHistoryUpdateReqVO vo,HttpServletRequest request){
        String tokenStr=request.getHeader(Constant.ACCESS_TOKEN);
        String userId=JwtTokenUtil.getUserId(tokenStr);
        peoplePastHistoryService.PeoplePastHistoryUpdate(vo,userId);
        return DataResult.success();
    }

    @GetMapping("/peoplepasthistory/{pid}")
    @ApiOperation(value = "根据pid获取既往史接口")
    @MyLog(title = "老人建档管理-信息登记",action = "根据pid获取既往史接口-既往史接口")
    public  DataResult<PeoplePastHistory> getPastHistoryByPid(@PathVariable("pid") String pid){
        DataResult<PeoplePastHistory> result= DataResult.success();
        result.setData(peoplePastHistoryService.getPastHistoryByPid(pid));
        return result;
    }
}
