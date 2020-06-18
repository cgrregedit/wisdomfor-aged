package com.cgr.lesson.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 21:06 2020-04-30
 * @ Description：测试Hibernate Validator
 * @ Modified By：
 */
@Data
public class TestReqVO {
    @ApiModelProperty(value = "名称")
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "年龄")
    @NotNull(message = "年龄不能为空")
    private Integer age;

    @ApiModelProperty(value = "集合")
    @NotEmpty(message = "集合不能为空")
    private List<String> list;
}
