package com.cgr.lesson.vo.resp;

import com.cgr.lesson.entity.CareSetting;
import com.cgr.lesson.entity.CareSettingDetail;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 21:13 2020-06-15
 * @ Description：
 * @ Modified By：
 */
@Data
public class CareSettingResqVO {
    @ApiModelProperty("护理级别主表")
    private CareSetting careSetting;

    @ApiModelProperty("护理级别从表")
    private List<CareSettingDetail> careSettingDetailList;
}
