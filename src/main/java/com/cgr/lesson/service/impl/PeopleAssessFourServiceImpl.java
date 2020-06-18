package com.cgr.lesson.service.impl;


import com.cgr.lesson.entity.PeopleAssessFour;
import com.cgr.lesson.exception.BusinessException;
import com.cgr.lesson.exception.code.BaseResponseCode;
import com.cgr.lesson.mapper.PeopleAssessFourMapper;
import com.cgr.lesson.service.PeopleAssessFourService;
import com.cgr.lesson.vo.req.PeopleAssessFourAddReqVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 22:59 2020-06-03
 * @ Description：
 * @ Modified By：
 */

@Service
@Slf4j
public class PeopleAssessFourServiceImpl implements PeopleAssessFourService {
    @Autowired
    private PeopleAssessFourMapper peopleAssessFourMapper;

    @Override
    public PeopleAssessFour peopleAssessFourAdd(PeopleAssessFourAddReqVO vo, String userId) {
        PeopleAssessFour peopleAssessFour=new PeopleAssessFour();
        BeanUtils.copyProperties(vo,peopleAssessFour);
        peopleAssessFour.setId(UUID.randomUUID().toString());
        peopleAssessFour.setCreateId(userId);
        peopleAssessFour.setCreateTime(new Date());
        int totalscore=vo.getLiving()+vo.getWorking()+vo.getTimeConcept()+vo.getCharacterOrientation()+vo.getSocialInteraction();
        peopleAssessFour.setTotalScore(totalscore);
        int grade;
        if (totalscore>=0 && totalscore<=2){
            grade=0;
        }else  if (totalscore>=3 && totalscore<=7){
            grade=1;
        }else  if (totalscore>=8 && totalscore<=13){
            grade=2;
        }
        else  if (totalscore>=14 && totalscore<=20){
            grade=3;
        }
        int i=peopleAssessFourMapper.insertSelective(peopleAssessFour);
        if (i!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        return peopleAssessFour;
    }
}
