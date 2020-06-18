package com.cgr.lesson.service.impl;

import com.cgr.lesson.entity.PeopleAssessFive;
import com.cgr.lesson.exception.BusinessException;
import com.cgr.lesson.exception.code.BaseResponseCode;
import com.cgr.lesson.mapper.PeopleAssessFiveMapper;
import com.cgr.lesson.service.PeopleAssessFiveService;
import com.cgr.lesson.vo.req.PeopleAssessFiveReqVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 21:24 2020-06-10
 * @ Description：长者能力
 * @ Modified By：
 */

@Service
public class PeopleAssessFiveServiceImpl implements PeopleAssessFiveService {
    @Autowired
    private PeopleAssessFiveMapper peopleAssessFiveMapper;

    @Override
    public PeopleAssessFive peopleAssessFiveAdd(PeopleAssessFiveReqVO vo, String userId) {
        PeopleAssessFive peopleAssessFive=new PeopleAssessFive();
        BeanUtils.copyProperties(vo,peopleAssessFive);
        peopleAssessFive.setId(UUID.randomUUID().toString());
        peopleAssessFive.setCreateId(userId);
        peopleAssessFive.setCreateTime(new Date());
        int i=peopleAssessFiveMapper.insertSelective(peopleAssessFive);
        if(i!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        return peopleAssessFive;
    }
}
