package com.cgr.lesson.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 00:33 2020-06-21
 * @ Description：
 * @ Modified By：
 */

@Data
public class FoodRecipesRespVO {
    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "菜品编号")
    private String foodId;

    @ApiModelProperty(value = "菜品名称")
    private String foodName;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "图片路径")
    private String photo;

    @ApiModelProperty(value = "辣味")
    private String piquancyName;

    @ApiModelProperty(value = "口感")
    private String mouthFeelName;

    @ApiModelProperty(value = "口味")
    private String tasteName;

    @ApiModelProperty(value = "禁忌人群")
    private String tabooPopulationName;
}
