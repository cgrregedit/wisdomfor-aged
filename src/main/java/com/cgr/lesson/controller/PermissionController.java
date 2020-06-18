package com.cgr.lesson.controller;

import com.cgr.lesson.aop.annotation.MyLog;
import com.cgr.lesson.entity.SysPermission;
import com.cgr.lesson.mapper.SysPermissionMapper;
import com.cgr.lesson.service.PermissionService;
import com.cgr.lesson.utils.DataResult;
import com.cgr.lesson.vo.req.PermissionUpdateReqVO;
import com.cgr.lesson.vo.resp.PermissionAddReqVO;
import com.cgr.lesson.vo.resp.PermissionRespNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.crypto.Data;
import java.util.List;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 16:06 2020-05-12
 * @ Description：菜单权限
 * @ Modified By：
 */
@RestController
@RequestMapping("/api")
@Api(tags = "组织模块-菜单权限管理")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/permissions")
    @ApiOperation("获取所有菜单权限接口")
    @MyLog(title = "组织模块-菜单权限管理",action = "获取所有菜单权限接口")
    public DataResult<List<SysPermission>> getAllMenusPermission() {
        DataResult<List<SysPermission>> dataResult = DataResult.success();
        dataResult.setData(permissionService.selectAll());
        return dataResult;
    }

    @GetMapping("/permission/tree")
    @ApiOperation(value = "获取所有目录菜单树接口-查到到目录")
    @MyLog(title = "组织模块-菜单权限管理",action = "获取所有目录菜单树接口-查到到目录")
    public DataResult<List<PermissionRespNode>> getAllMenusPermissionTree() {
        DataResult<List<PermissionRespNode>> result = DataResult.success();
        result.setData(permissionService.selectAllMenuByTree());
        return result;
    }

    @PostMapping("/permission")
    @ApiOperation(value = "新增菜单权限接口")
    @MyLog(title = "组织模块-菜单权限管理",action = "新增菜单权限接口")
    public DataResult<SysPermission> addPermission(@RequestBody @Valid PermissionAddReqVO permissionAddReqVO) {
        DataResult<SysPermission> dataResult = DataResult.success();
        dataResult.setData(permissionService.addPermission(permissionAddReqVO));
        return dataResult;
    }

    @PostMapping("/permission/tree/all")
    @ApiOperation(value = "获取所有目录菜单树接口-查到到按钮")
    @MyLog(title = "组织模块-菜单权限管理",action = "获取所有目录菜单树接口-查到到按钮")
    public DataResult<List<PermissionRespNode>> getAllPermissionTree() {
        DataResult<List<PermissionRespNode>> dataResult = DataResult.success();
        dataResult.setData(permissionService.selectAllByTree());
        return dataResult;
    }

    @PutMapping("/permission")
    @ApiOperation(value = "编辑菜单权限接口")
    @MyLog(title = "组织模块-菜单权限管理",action = "编辑菜单权限接口")
    public DataResult updatePermission(@RequestBody @Valid PermissionUpdateReqVO vo) {
        DataResult result = DataResult.success();
        permissionService.updatePermission(vo);
        return result;
    }

    @DeleteMapping("/permission/{permissionId}")
    @ApiOperation(value = "删除菜单权限接口")
    @MyLog(title = "组织模块-菜单权限管理",action = "删除菜单权限接口")
    public DataResult deletedPermission(@PathVariable("permissionId") String permissionId){
        DataResult dataResult=DataResult.success();
        permissionService.deletedPermission(permissionId);
        return dataResult;
    }

}
