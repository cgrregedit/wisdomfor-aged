package com.cgr.lesson.controller;

import com.cgr.lesson.aop.annotation.MyLog;
import com.cgr.lesson.entity.SysDept;
import com.cgr.lesson.service.DeptService;
import com.cgr.lesson.utils.DataResult;
import com.cgr.lesson.vo.req.DeptAddReqVO;
import com.cgr.lesson.vo.req.DeptUpdateReqVO;
import com.cgr.lesson.vo.resp.DeptRespNodeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 22:13 2020-05-17
 * @ Description：科室信息
 * @ Modified By：
 */

@RestController
@RequestMapping("/api")
@Api(tags = "组织模块-科室管理")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @GetMapping("/depts")
    @ApiOperation(value = "获取科室信息接口")
    @MyLog(title = "组织管理-科室管理",action = "获取科室信息接口")
    public DataResult<List<SysDept>> getDeptAll() {
        DataResult<List<SysDept>> dataResult = DataResult.success();
        dataResult.setData(deptService.selectAll());
        return dataResult;
    }

    @GetMapping("/dept/tree")
    @ApiOperation(value = "树形科室列表接口")
    @MyLog(title = "组织管理-科室管理",action = "树形科室列表接口")
    public DataResult<List<DeptRespNodeVO>> getTree(@RequestParam(required = false) String deptId) {
        DataResult<List<DeptRespNodeVO>> result = DataResult.success();
        result.setData(deptService.deptTreeList(deptId));
        return result;
    }

    @PostMapping("/dept")
    @ApiOperation(value = "新增科室信息接口")
    @MyLog(title = "组织管理-科室管理",action = "新增科室信息接口")
    public DataResult<SysDept> addDept(DeptAddReqVO vo) {
        DataResult<SysDept> dataResult = DataResult.success();
        dataResult.setData(deptService.addDept(vo));
        return dataResult;
    }

    @PutMapping("/dept")
    @ApiOperation(value = "编辑科室信息接口")
    @MyLog(title = "组织管理-科室管理",action = "编辑科室信息接口")
    public DataResult updateDep(@RequestBody @Valid DeptUpdateReqVO vo){
        deptService.updateDept(vo);
        return DataResult.success();
    }

    @DeleteMapping("/dept/{id}")
    @ApiOperation(value = "删除科室接口")
    @MyLog(title = "组织管理-科室管理",action = "删除科室接口")
    public DataResult deleteDep(@PathVariable("id") String id){
        deptService.deleted(id);
        return DataResult.success();
    }
}
