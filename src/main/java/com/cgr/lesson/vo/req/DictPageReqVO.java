package com.cgr.lesson.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 10:05 2020-05-30
 * @ Description：数字字典分页查询
 * @ Modified By：
 */
@Data
public class DictPageReqVO {
    @ApiModelProperty(value = "第几页")
    private int pageNum = 1;

    @ApiModelProperty(value = "分页数量")
    private int pageSize = 10;

    @ApiModelProperty(value = "类型")
    private String typename;
}
