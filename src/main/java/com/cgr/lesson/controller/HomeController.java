package com.cgr.lesson.controller;

import com.cgr.lesson.aop.annotation.MyLog;
import com.cgr.lesson.constants.Constant;
import com.cgr.lesson.service.HomeService;
import com.cgr.lesson.utils.DataResult;
import com.cgr.lesson.utils.JwtTokenUtil;
import com.cgr.lesson.vo.resp.HomeRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 15:19 2020-05-12
 * @ Description：首页
 * @ Modified By：
 */
@RestController
@RequestMapping("/api")
@Api(tags = "首页数据")
public class HomeController {
  @Autowired
  private HomeService homeService;

  @PostMapping("/home")
  @ApiOperation(value = "获取首页数据接口")
  @MyLog(title = "首页数据",action = "获取首页数据接口")
  public DataResult<HomeRespVO> getHomeInfo(HttpServletRequest httpServletRequest){
     String accessToken=httpServletRequest.getHeader(Constant.ACCESS_TOKEN);
     String userID= JwtTokenUtil.getUserId(accessToken);
     DataResult<HomeRespVO> dataResult=DataResult.success();
     dataResult.setData(homeService.getHomeInfo(userID));
     return dataResult;
  }
}
