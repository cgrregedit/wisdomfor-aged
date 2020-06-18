package com.cgr.lesson.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 14:47 2020-05-12
 * @ Description：
 * @ Modified By：
 */
@Data
public class PermissionRespNode {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "菜单权限名称")
    private String title;
    @ApiModelProperty(value = "接口地址")
    private String url;
    private List<?> children;
    @ApiModelProperty(value = "是否展开 默认不展开(false)")
    private boolean spread=true;
    @ApiModelProperty(value = "是否选中 默认false")
    private boolean checked;
}
