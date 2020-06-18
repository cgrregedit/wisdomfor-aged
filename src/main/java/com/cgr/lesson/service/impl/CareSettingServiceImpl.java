package com.cgr.lesson.service.impl;

import com.cgr.lesson.entity.CareSetting;
import com.cgr.lesson.entity.CareSettingDetail;
import com.cgr.lesson.exception.BusinessException;
import com.cgr.lesson.exception.code.BaseResponseCode;
import com.cgr.lesson.mapper.CareSettingDetailMapper;
import com.cgr.lesson.mapper.CareSettingMapper;
import com.cgr.lesson.service.CareSettingDetailService;
import com.cgr.lesson.service.CareSettingService;
import com.cgr.lesson.utils.PageUtil;
import com.cgr.lesson.vo.req.CareProReqVO;
import com.cgr.lesson.vo.req.CareSettingDetailReqVO;
import com.cgr.lesson.vo.req.CareSettingReqVO;
import com.cgr.lesson.vo.resp.CareProResqVO;
import com.cgr.lesson.vo.resp.CareSettingResqVO;
import com.cgr.lesson.vo.resp.PageVO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 15:06 2020-06-15
 * @ Description：护理级别设置
 * @ Modified By：
 */

@Service
public class CareSettingServiceImpl implements CareSettingService {
    @Autowired
    private CareSettingMapper careSettingMapper;

    @Autowired
    private CareSettingDetailService careSettingDetailService;

    @Autowired
    private CareSettingDetailMapper careSettingDetailMapper;


    //分页查询护理项目
    @Override
    public PageVO<CareProResqVO> getCareProInfo(CareProReqVO vo) {
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<CareProResqVO> list=careSettingMapper.selectByFind(vo);
        return PageUtil.getPageVO(list);
    }

    //护理级别设置新增
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void CareSettingAdd(CareSettingReqVO vo,String userID) {
        CareSetting careSetting=new CareSetting();
        BeanUtils.copyProperties(vo,careSetting);
        //查询是否有重复的名称

        String careID= UUID.randomUUID().toString();
        careSetting.setId(careID);
        careSetting.setCreateId(userID);
        careSetting.setCreateTime(new Date());
        int i=careSettingMapper.insertSelective(careSetting);
        if(i!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        //增加执行计划
        List<CareSettingDetail> list=new ArrayList<>();
        for (CareSettingDetailReqVO careSettingDetailReqVO:vo.getCareSettingDetail()){
            CareSettingDetail careSettingDetail=new CareSettingDetail();
            BeanUtils.copyProperties(careSettingDetailReqVO,careSettingDetail);
            careSettingDetail.setId(UUID.randomUUID().toString());
            careSettingDetail.setPid(careID);
            careSettingDetail.setCreateId(userID);
            careSettingDetail.setCreateTime(new Date());
            list.add(careSettingDetail);
         }

        //批量插入执行计划
        int count = careSettingDetailService.batchInsert(list);

        if(count==0){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
    }

    @Override
    public List<String> selectAllName() {
        return careSettingMapper.selectAllName();
    }

    //根据名称获取护理级别
    @Override
    public CareSettingResqVO getCareSettingByName(String name) {
        CareSettingResqVO careSettingResqVO=new CareSettingResqVO();
        careSettingResqVO.setCareSetting(careSettingMapper.getCareSettingByName(name));
        careSettingResqVO.setCareSettingDetailList(careSettingDetailMapper.getCareSettingDetailByName(name));
        return careSettingResqVO;
    }
}
