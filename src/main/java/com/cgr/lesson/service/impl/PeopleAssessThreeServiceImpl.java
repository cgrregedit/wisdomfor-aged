package com.cgr.lesson.service.impl;

import com.cgr.lesson.entity.PeopleAssessThree;
import com.cgr.lesson.exception.BusinessException;
import com.cgr.lesson.exception.code.BaseResponseCode;
import com.cgr.lesson.mapper.PeopleAssessThreeMapper;
import com.cgr.lesson.service.PeopleAssessThreeService;
import com.cgr.lesson.vo.req.PeopleAssessThreeAddReqVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 10:39 2020-06-02
 * @ Description：感知觉与沟通评估表
 * @ Modified By：
 */
@Service
@Slf4j
public class PeopleAssessThreeServiceImpl implements PeopleAssessThreeService {
    @Autowired
    private PeopleAssessThreeMapper peopleAssessThreeMapper;

    @Override
    public PeopleAssessThree peopleAssessThreeAdd(PeopleAssessThreeAddReqVO vo, String userId) {
        PeopleAssessThree peopleAssessThree=new PeopleAssessThree();
        BeanUtils.copyProperties(vo,peopleAssessThree);
        peopleAssessThree.setId(UUID.randomUUID().toString());
        peopleAssessThree.setCreateId(userId);
        peopleAssessThree.setCreateTime(new Date());
        //分级
        int grade=0;
        if (vo.getAwarenessLevel()==0 && vo.getCommunication()==0){
            if (vo.getVisualAcuity()==0 || vo.getHearing()==1){
                grade=0;
            }
        }
        if (vo.getAwarenessLevel()==0){
            if (vo.getVisualAcuity()==2 || vo.getHearing()==2){
                grade=1;
            }
            if (vo.getCommunication()==1){
                grade=1;
            }
        }
        if (vo.getAwarenessLevel()==0){
            if (vo.getVisualAcuity()==3 || vo.getHearing()==3){
                grade=1;
            }
            if (vo.getCommunication()==2){
                grade=2;
            }
        }
        if (vo.getAwarenessLevel()==2 || vo.getAwarenessLevel()==3){
            grade=3;
        }
        if (vo.getAwarenessLevel()==0 || vo.getAwarenessLevel()==1){
            if (vo.getVisualAcuity()==4 || vo.getHearing()==4){
                grade=3;
            }
            if (vo.getCommunication()==3){
                grade=3;
            }
        }
        peopleAssessThree.setGrade(grade);
        int i=peopleAssessThreeMapper.insertSelective(peopleAssessThree);
        if (i!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        return peopleAssessThree;
    }
}
