package com.cgr.lesson.controller;

import com.cgr.lesson.aop.annotation.MyLog;
import com.cgr.lesson.constants.Constant;
import com.cgr.lesson.entity.FoodDishes;
import com.cgr.lesson.service.FoodDishesService;
import com.cgr.lesson.utils.DataResult;
import com.cgr.lesson.utils.JwtTokenUtil;
import com.cgr.lesson.vo.req.FoodDishesAddReqVO;
import com.cgr.lesson.vo.req.FoodDishesPageReqVO;
import com.cgr.lesson.vo.req.FoodDishesUpdateVO;
import com.cgr.lesson.vo.resp.FoodDishesRespVO;
import com.cgr.lesson.vo.resp.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.crypto.Data;

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

    //分页查询
    @PostMapping("/fooddishess")
    @ApiOperation(value = "分页查询菜品接口")
    @MyLog(title = "菜品管理",action = "分页查询菜品")
    public DataResult<PageVO<FoodDishesRespVO>> pageInfo(@RequestBody FoodDishesPageReqVO vo){
        DataResult<PageVO<FoodDishesRespVO>> result=DataResult.success();
        result.setData(foodDishesService.pageInfo(vo));
        return result;
    }

    //编辑
    @PutMapping("/fooddishes")
    @ApiOperation(value = "菜品编辑接口")
    @MyLog(title = "菜品管理",action = "菜品编辑")
    public DataResult<FoodDishes> foodDishesUpdate(@RequestBody @Valid FoodDishesUpdateVO vo,HttpServletRequest request){
        String tokenStr=request.getHeader(Constant.ACCESS_TOKEN);
        String userId=JwtTokenUtil.getUserId(tokenStr);
        DataResult<FoodDishes> result=DataResult.success();
        result.setData(foodDishesService.foodDishesUpdate(vo,userId));
        return  result;
    }

    //删除
    @DeleteMapping("/fooddishes/{id}")
    @ApiOperation(value = "菜品删除接口")
    @MyLog(title = "菜品管理",action = "菜品删除")
    public DataResult foodDishesDelete(@PathVariable("id") String id){
        foodDishesService.foodDishesDelete(id);
        return DataResult.success();
    }

}
