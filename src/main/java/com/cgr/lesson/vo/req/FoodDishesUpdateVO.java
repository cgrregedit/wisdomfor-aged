package com.cgr.lesson.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 20:03 2020-06-20
 * @ Description：菜品编辑接受类
 * @ Modified By：
 */
@Data
public class FoodDishesUpdateVO {
    @ApiModelProperty("ID")
    @NotBlank(message = "ID不能为空")
    private String id;

    @ApiModelProperty("名称")
    @NotBlank(message = "名称不能为空")
    private String foodName;

    @ApiModelProperty("价格")
    @NotNull(message = "价格不能为空")
    private BigDecimal price;

    @ApiModelProperty("图片文件名")
    @NotBlank(message = "图片文件名不能为空")
    private String photo;

    @ApiModelProperty("辣味")
    private Integer piquancy;

    @ApiModelProperty("口感")
    private Integer mouthFeel;

    @ApiModelProperty("口味")
    private Integer taste;

    @ApiModelProperty("禁忌人群")
    private String tabooPopulation;

    @ApiModelProperty("说明")
    private String foodNote;
}
