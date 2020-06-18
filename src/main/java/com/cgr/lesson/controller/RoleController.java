package com.cgr.lesson.controller;

import com.cgr.lesson.aop.annotation.MyLog;
import com.cgr.lesson.entity.SysRole;
import com.cgr.lesson.service.RoleService;
import com.cgr.lesson.utils.DataResult;
import com.cgr.lesson.vo.req.RoleAddReqVO;
import com.cgr.lesson.vo.req.RolePageReqVO;
import com.cgr.lesson.vo.req.RoleUpdateReqVO;
import com.cgr.lesson.vo.resp.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 11:52 2020-05-14
 * @ Description：
 * @ Modified By：
 */
@RestController
@RequestMapping("/api")
@Api(tags = "组织模块-角色管理")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @PostMapping("/roles")
    @ApiOperation(value = "分页获取角色信息接口")
    @MyLog(title = "组织模块-角色管理",action = "分页获取角色信息接口")
    public DataResult<PageVO<SysRole>> pageInfo(@RequestBody RolePageReqVO rolePageReqVO){
        DataResult<PageVO<SysRole>> dataResult=DataResult.success();
        dataResult.setData(roleService.pageInfo(rolePageReqVO));
        return dataResult;
    }

    @PostMapping("/role")
    @ApiOperation(value = "新增角色接口")
    @MyLog(title = "组织模块-角色管理",action = "新增角色接口")
    public  DataResult<SysRole> addRole(@RequestBody @Valid RoleAddReqVO roleAddReqVO){
        DataResult<SysRole> dataResult=DataResult.success();
        dataResult.setData(roleService.addRole(roleAddReqVO));
        return dataResult;
    }

    @GetMapping("/role/{id}")
    @ApiOperation(value = "获取角色详情接口")
    @MyLog(title = "组织模块-角色管理",action = "获取角色详情接口")
    public DataResult<SysRole> detailInfo(@PathVariable("id") String id){
       DataResult<SysRole> dataResult=DataResult.success();
       dataResult.setData(roleService.detailInfo(id));
       return dataResult;
    }

    @PutMapping("/role")
    @ApiOperation(value = "编辑角色信息接口")
    @MyLog(title = "组织模块-角色管理",action = "编辑角色信息接口")
    public DataResult updateDept(@RequestBody @Valid RoleUpdateReqVO vo){
        roleService.updateRole(vo);
        return DataResult.success();
    }


    @DeleteMapping("/role/{id}")
    @ApiOperation(value = "删除角色接口")
    @MyLog(title = "组织模块-角色管理",action = "删除角色接口")
    public DataResult deleted(@PathVariable("id") String id){
        roleService.deletedRole(id);
        return DataResult.success();
    }

}
