package com.cgr.lesson.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 14:40 2020-06-15
 * @ Description：
 * @ Modified By：
 */

@Data
public class CareProReqVO {
    @ApiModelProperty(value = "第几页")
    private int pageNum = 1;

    @ApiModelProperty(value = "分页数量")
    private int pageSize = 10;

    @ApiModelProperty(value = "查询内容")
    private String findText;
}
