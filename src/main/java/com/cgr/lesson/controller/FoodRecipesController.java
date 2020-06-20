package com.cgr.lesson.controller;

import com.cgr.lesson.aop.annotation.MyLog;
import com.cgr.lesson.service.FoodRecipesService;
import com.cgr.lesson.utils.DataResult;
import com.cgr.lesson.vo.req.FoodRecipesAddReqVO;
import com.cgr.lesson.vo.resp.FoodRecipesRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 23:44 2020-06-20
 * @ Description：菜谱
 * @ Modified By：
 */

@RestController
@RequestMapping("/api")
@Api(tags = "菜品管理")
public class FoodRecipesController {
    @Autowired
    private FoodRecipesService foodRecipesService;

    @PostMapping("/foodrecipes")
    @ApiOperation(value = "菜谱新增接口")
    @MyLog(title = "菜品管理",action = "菜谱新增")
    public DataResult foodRecipesAdd(@RequestBody @Valid  FoodRecipesAddReqVO vo){
        foodRecipesService.batchInsert(vo);
        return DataResult.success();
    }

    @GetMapping("/foodrecipes/{week}")
    @ApiOperation(value = "根据星期几查询菜谱接口")
    @MyLog(title = "菜品管理",action = "根据星期几查询菜谱")
    public DataResult<List<FoodRecipesRespVO>> selectByWeek(@PathVariable("week") String week){
        DataResult<List<FoodRecipesRespVO>> result=DataResult.success();
        result.setData(foodRecipesService.selectByWeek(week));
        return  result;
    }
}
