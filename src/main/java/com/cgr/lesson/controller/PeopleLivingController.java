package com.cgr.lesson.controller;

import com.cgr.lesson.aop.annotation.MyLog;
import com.cgr.lesson.constants.Constant;
import com.cgr.lesson.entity.PeopleLiving;
import com.cgr.lesson.service.PeopleLivingService;
import com.cgr.lesson.utils.DataResult;
import com.cgr.lesson.utils.JwtTokenUtil;
import com.cgr.lesson.vo.req.PeopleLivingAddReqVO;
import com.cgr.lesson.vo.req.PeopleLivingUpdateReqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 00:33 2020-05-29
 * @ Description：生活环境
 * @ Modified By：
 */

@RestController
@RequestMapping("/api")
@Api(tags = "老人建档管理-信息登记")
public class PeopleLivingController {
    @Autowired
    private PeopleLivingService peopleLivingService;

    @PostMapping("/peopleliving")
    @ApiOperation(value = "生活习惯新增接口")
    @MyLog(title = "老人建档管理-信息登记",action ="新增信息登记-生活习惯接口" )
    public DataResult<PeopleLiving> peopleLivingAdd(@RequestBody @Valid PeopleLivingAddReqVO vo, HttpServletRequest request){
        String tokenStr=request.getHeader(Constant.ACCESS_TOKEN);
        String userId= JwtTokenUtil.getUserId(tokenStr);
        DataResult<PeopleLiving> result=DataResult.success();
        result.setData(peopleLivingService.peopleLivingAdd(vo,userId));
        return result;
    }

    @PutMapping("/peopleliving")
    @ApiOperation(value = "生活习惯编辑接口")
    @MyLog(title = "老人建档管理-信息登记",action ="编辑信息登记-生活习惯接口" )
    public DataResult peopleLivingUpdate(@RequestBody @Valid PeopleLivingUpdateReqVO vo,HttpServletRequest request){
        String tokenStr=request.getHeader(Constant.ACCESS_TOKEN);
        String userId=JwtTokenUtil.getUserId(tokenStr);
        peopleLivingService.peopleLivingUpdate(vo,userId);
        return DataResult.success();
    }

    @GetMapping("/peopleliving/{pid}")
    @ApiOperation(value = "根据pid获取生活习惯接口")
    @MyLog(title = "老人建档管理-信息登记",action ="根据pid获取生活习惯-生活习惯接口" )
    public DataResult<PeopleLiving> getLivingByPid(@PathVariable("pid") String pid){
        DataResult<PeopleLiving> result=DataResult.success();
        result.setData(peopleLivingService.getLivingByPid(pid));
        return result;
    }
}
