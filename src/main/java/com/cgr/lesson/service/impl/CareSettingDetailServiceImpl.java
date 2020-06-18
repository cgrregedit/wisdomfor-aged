package com.cgr.lesson.service.impl;

import com.cgr.lesson.entity.CareSettingDetail;
import com.cgr.lesson.mapper.CareSettingDetailMapper;
import com.cgr.lesson.service.CareSettingDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 16:26 2020-06-15
 * @ Description：
 * @ Modified By：
 */
@Service
public class CareSettingDetailServiceImpl implements CareSettingDetailService {
    @Autowired
    private CareSettingDetailMapper careSettingDetailMapper;

    @Override
    public int batchInsert(List<CareSettingDetail> list) {
        return  careSettingDetailMapper.batchInsert(list);
    }
}
