package com.cgr.lesson.controller;

import com.cgr.lesson.aop.annotation.MyLog;
import com.cgr.lesson.constants.Constant;
import com.cgr.lesson.entity.FoodDishes;
import com.cgr.lesson.service.FoodDishesService;
import com.cgr.lesson.utils.DataResult;
import com.cgr.lesson.utils.JwtTokenUtil;
import com.cgr.lesson.vo.req.FoodDishesAddReqVO;
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
 * @ Date       ：Created in 16:19 2020-06-20
 * @ Description：菜品设置
 * @ Modified By：
 */

@RestController
@RequestMapping("/api")
@Api(tags = "菜品管理")
public class FoodDishesController {
    @Autowired
    private FoodDishesService foodDishesService;

    @PostMapping("/fooddishes")
    @ApiOperation(value = "菜品新增接口")
    @MyLog(title = "菜品管理",action = "菜品新增")
    public DataResult<FoodDishes> foodDisshesAdd(@RequestBody @Valid FoodDishesAddReqVO vo, HttpServletRequest request){
        String tonkenStr=request.getHeader(Constant.ACCESS_TOKEN);
        String userId= JwtTokenUtil.getUserId(tonkenStr);
        DataResult<FoodDishes> result=DataResult.success();
        result.setData(foodDishesService.foodDishesAdd(vo,userId));
        return result;
    }

}
