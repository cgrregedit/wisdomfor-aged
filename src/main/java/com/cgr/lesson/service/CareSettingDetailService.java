package com.cgr.lesson.service;

import com.cgr.lesson.entity.CareSettingDetail;

import java.util.List;

public interface CareSettingDetailService {
    int batchInsert(List<CareSettingDetail> list);
}
