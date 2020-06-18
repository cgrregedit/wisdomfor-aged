package com.cgr.lesson.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 14:45 2020-05-12
 * @ Description：部门树响应前段数据 VO
 * @ Modified By：
 */
@Data
public class DeptRespNodeVO {

    @ApiModelProperty(value = "部门id")
    private String id;

    @ApiModelProperty(value = "部门名称")
    private String title;

    @ApiModelProperty("是否展开 默认true")
    private boolean spread=true;

    @ApiModelProperty(value = "子集叶子节点")
    private List<?> children;
}
