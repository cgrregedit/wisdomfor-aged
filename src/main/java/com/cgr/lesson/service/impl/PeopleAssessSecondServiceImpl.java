package com.cgr.lesson.service.impl;

import com.cgr.lesson.entity.PeopleAssessSecond;
import com.cgr.lesson.exception.BusinessException;
import com.cgr.lesson.exception.code.BaseResponseCode;
import com.cgr.lesson.mapper.PeopleAssessSecondMapper;
import com.cgr.lesson.service.PeopleAssessSecondService;
import com.cgr.lesson.vo.req.PeopleAssessSecondAddReqVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 21:49 2020-06-01
 * @ Description：
 * @ Modified By：
 */
@Service
public class PeopleAssessSecondServiceImpl implements PeopleAssessSecondService {
    @Autowired
    private PeopleAssessSecondMapper peopleAssessSecondMapper;

    @Override
    public PeopleAssessSecond peopleAssessSecondAdd(PeopleAssessSecondAddReqVO vo, String userId) {
        PeopleAssessSecond peopleAssessSecond=new PeopleAssessSecond();
        BeanUtils.copyProperties(vo,peopleAssessSecond);
        peopleAssessSecond.setId(UUID.randomUUID().toString());
        peopleAssessSecond.setCreateId(userId);
        peopleAssessSecond.setCreateTime(new Date());
        //总得分
        int total_Score=vo.getCognitive()+vo.getAttacks()+vo.getSymptoms();
        peopleAssessSecond.setTotalScore(total_Score);
        //精神状态分级
        int grade=0;
        if (total_Score==0){
          grade=0;
        }else if(total_Score==1)
        {
            grade=1;
        }else if(total_Score==2 || total_Score==3){
            grade=2;
        }else if(total_Score>=4 && total_Score<=6){
            grade=3;
        }
        peopleAssessSecond.setGrade(grade);
        int i=peopleAssessSecondMapper.insertSelective(peopleAssessSecond);
        if (i!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        return peopleAssessSecond;
    }
}
