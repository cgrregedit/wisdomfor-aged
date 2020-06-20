package com.cgr.lesson.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 17:12 2020-06-20
 * @ Description：菜品设置返回类
 * @ Modified By：
 */
@Data
public class FoodDishesRespVO {
    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "名称")
    private String foodName;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "图片名")
    private String photo;

    @ApiModelProperty(value = "辣味")
    private Integer piquancy;

    @ApiModelProperty(value = "口感")
    private Integer mouthFeel;

    @ApiModelProperty(value = "口感")
    private Integer taste;

    @ApiModelProperty(value = "禁忌人群")
    private String tabooPopulation;

    @ApiModelProperty(value = "说明")
    private String foodNote;

    @ApiModelProperty(value = "是否删除")
    private Integer deleted;

    @ApiModelProperty(value = "创建人")
    private String createId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新人")
    private String updateId;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "备注1")
    private String remark1;

    @ApiModelProperty(value = "备注2")
    private String remark2;

    @ApiModelProperty(value = "备注3")
    private String remark3;

    @ApiModelProperty(value = "备注4")
    private String remark4;

    @ApiModelProperty(value = "备注5")
    private String remark5;

    @ApiModelProperty(value = "辣味名")
    private String piquancyName;

    @ApiModelProperty(value = "口感名")
    private String mouthFeelName;

    @ApiModelProperty(value = "口味名")
    private String tasteName;

    @ApiModelProperty(value = "禁忌人群名")
    private String tabooPopulationName;
}
