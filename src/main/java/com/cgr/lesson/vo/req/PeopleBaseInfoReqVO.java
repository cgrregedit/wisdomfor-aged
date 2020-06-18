package com.cgr.lesson.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 00:33 2020-05-26
 * @ Description：
 * @ Modified By：
 */
@Data
public class PeopleBaseInfoReqVO {
    @ApiModelProperty(value = "姓名")
    @NotBlank(message = "姓名不能为空")
    private String name;

    @ApiModelProperty(value = "性别")
    @NotNull(message = "性别不能为空")
    private Integer sex;

    @ApiModelProperty(value = "出生年月")
    private Date birthday;

    @ApiModelProperty(value = "身份证号")
    @NotBlank(message = "身份证号不能为空")
    private String idcard;

    @ApiModelProperty(value = "工作单位")
    private String workPlace;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "联系人")
    private String contactsName;

    @ApiModelProperty(value = "联系人电话")
    private String contactsPhone;

    @ApiModelProperty(value = "常住类型")
    private Integer residenceType;

    @ApiModelProperty(value = "民族")
    private String nation;

    @ApiModelProperty(value = "血型")
    private Integer blood;

    @ApiModelProperty(value = "RH")
    private Integer bloodRh;

    @ApiModelProperty(value = "文化程度")
    private String education;

    @ApiModelProperty(value = "职业")
    private Integer profession;

    @ApiModelProperty(value = "婚姻状况")
    private Integer maritalStatus;

    @ApiModelProperty(value = "医疗费用支付方式")
    private Integer payType;

    @ApiModelProperty(value = "医疗费用支付方其他")
    private String payText;

    @ApiModelProperty(value = "药物过敏史")
    private Integer allergicHistory;

    @ApiModelProperty(value = "暴露史")
    private Integer exposureHistory;

    @ApiModelProperty(value = "医疗证号")
    private String medicalCard;
}
