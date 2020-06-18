package com.cgr.lesson.service.impl;

import com.cgr.lesson.entity.PeopleLiving;
import com.cgr.lesson.exception.BusinessException;
import com.cgr.lesson.exception.code.BaseResponseCode;
import com.cgr.lesson.mapper.PeopleLivingMapper;
import com.cgr.lesson.service.PeopleLivingService;
import com.cgr.lesson.vo.req.PeopleLivingAddReqVO;
import com.cgr.lesson.vo.req.PeopleLivingUpdateReqVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 00:15 2020-05-29
 * @ Description：生活环境新增
 * @ Modified By：
 */
@Service
@Slf4j
public class PeopleLivingServiceImpl implements PeopleLivingService {
    @Autowired
    private PeopleLivingMapper peopleLivingMapper;

    @Override
    public PeopleLiving peopleLivingAdd(PeopleLivingAddReqVO vo, String userId) {
        PeopleLiving peopleLiving=new PeopleLiving();
        BeanUtils.copyProperties(vo,peopleLiving);
        peopleLiving.setId(UUID.randomUUID().toString());
        peopleLiving.setCreateId(userId);
        peopleLiving.setCreateTime(new Date());
        int i=peopleLivingMapper.insertSelective(peopleLiving);
        if (i!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        return peopleLiving;
    }

    @Override
    public void peopleLivingUpdate(PeopleLivingUpdateReqVO vo, String userId) {
        PeopleLiving peopleLiving=peopleLivingMapper.selectByPrimaryKey(vo.getId());
        if (null == peopleLiving){
            log.error("传入的id:{}不合法",vo.getId());
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        BeanUtils.copyProperties(vo,peopleLiving);
        peopleLiving.setUpdateId(userId);
        peopleLiving.setUpdateTime(new Date());
        int i=peopleLivingMapper.updateByPrimaryKeySelective(peopleLiving);
        if (i!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
    }

    @Override
    public PeopleLiving getLivingByPid(String pid) {
        return peopleLivingMapper.getLivingByPid(pid);
    }
}
