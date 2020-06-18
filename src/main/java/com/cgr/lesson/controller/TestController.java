package com.cgr.lesson.controller;

import com.cgr.lesson.utils.DataResult;
import com.cgr.lesson.vo.req.TestReqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 22:48 2020-05-11
 * @ Description：
 * @ Modified By：
 */

@RestController
@RequestMapping("/test")
@Api(tags = "测试模块")
public class TestController {
    @GetMapping("/cgr")
    public String testAdd(){
        String s="Hello Cgr";
        return s;
    }

    @GetMapping("/index")
    @ApiOperation(value = "swagger测试index接口")
    public String testResult() {
        return "Hello World";
    }

    @GetMapping("/home")
    @ApiOperation(value = "DataResult测试")
    public DataResult<String> getHome(){
        DataResult<String> dataResult=DataResult.success("DataResult测试成功");
        return dataResult;
    }

    @GetMapping("/ex")
    @ApiOperation(value = "全局统一异常测试")
    public DataResult<String> getEx(){
        int i=1/0;
        DataResult<String> dataResult=DataResult.success("全局统一异常处理");
        return  dataResult;
    }

    @PostMapping("/Validator")
    @ApiOperation("Validator测试")
    public DataResult<String> testValidator(@RequestBody @Valid TestReqVO testReqVO){
        DataResult dataResult=DataResult.success();
        return  dataResult;
    }
}
