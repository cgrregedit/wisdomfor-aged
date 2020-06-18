package com.cgr.lesson.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 14:29 2020-06-15
 * @ Description：护理项目
 * @ Modified By：
 */

@Data
public class CareProResqVO {
    @ApiModelProperty("项目编号")
    private String tpid;

    @ApiModelProperty("项目名称")
    private String tpname;

    @ApiModelProperty("项目类型")
    private String dictname;

    @ApiModelProperty("单位")
    private String unit;

    @ApiModelProperty("价格")
    private Double price;

    @ApiModelProperty("拼音码")
    private String pycode;

    @ApiModelProperty("五笔码")
    private String wbcode;
}
