package com.cgr.lesson.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 14:45 2020-05-12
 * @ Description：用户首页导航栏信息
 * @ Modified By：
 */
@Data
public class HomeRespVO {
    @ApiModelProperty(value = "用户信息")
    private UserInfoRespVO userInfo;
    @ApiModelProperty(value = "目录菜单")
    private List<PermissionRespNode> menus;
}
