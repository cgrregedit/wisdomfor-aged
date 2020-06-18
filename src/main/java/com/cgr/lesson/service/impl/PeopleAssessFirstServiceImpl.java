package com.cgr.lesson.service.impl;

import com.cgr.lesson.entity.PeopleAssessFirst;
import com.cgr.lesson.exception.BusinessException;
import com.cgr.lesson.exception.code.BaseResponseCode;
import com.cgr.lesson.mapper.PeopleAssessFirstMapper;
import com.cgr.lesson.service.PeopleAssessFirstService;
import com.cgr.lesson.vo.req.PeopleAssessFirstAddReqVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 13:47 2020-05-30
 * @ Description：
 * @ Modified By：
 */
@Service
@Slf4j
public class PeopleAssessFirstServiceImpl implements PeopleAssessFirstService {
    @Autowired
    private PeopleAssessFirstMapper peopleAssessFirstMapper;
    @Override
    public PeopleAssessFirst peopleAssessFirstAdd(PeopleAssessFirstAddReqVO vo,String userId) {
        PeopleAssessFirst peopleAssessFirst=new PeopleAssessFirst();
        BeanUtils.copyProperties(vo,peopleAssessFirst);
        peopleAssessFirst.setId(UUID.randomUUID().toString());
        peopleAssessFirst.setCreateId(userId);
        peopleAssessFirst.setCreateTime(new Date());
        //总得分
        Integer totalScore=vo.getEating()+vo.getBath()+vo.getModification()+vo.getClothing()+vo.getShit()
                          +vo.getUrination()+vo.getToilet()+vo.getBedTransfer()+vo.getWalking() +vo.getStairs();
        peopleAssessFirst.setTotalScore(totalScore);
        //分级
        Integer grade=0;
        if (totalScore>95){
            grade=0;
        }else if (totalScore>65 && totalScore<=95){
            grade=1;
        }else if (totalScore>45 && totalScore<=65){
            grade=2;
        }else if (totalScore<=45){
            grade=3;
        }
        peopleAssessFirst.setGrade(grade);
        int i=peopleAssessFirstMapper.insertSelective(peopleAssessFirst);
        if (i!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        return peopleAssessFirst;
    }
}
