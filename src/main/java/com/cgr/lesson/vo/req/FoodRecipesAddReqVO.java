package com.cgr.lesson.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 21:47 2020-06-20
 * @ Description：菜谱接受类
 * @ Modified By：
 */
@Data
public class FoodRecipesAddReqVO {
    @ApiModelProperty("星期几")
    @NotBlank(message = "星期几不能为空")
    private String week;

    @ApiModelProperty("菜品ID")
    @NotEmpty(message = "菜品ID不能为空")
    private List<String> foodIds;
}
