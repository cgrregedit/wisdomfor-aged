package com.cgr.lesson.service.impl;

import com.cgr.lesson.entity.PeopleAssess;
import com.cgr.lesson.exception.BusinessException;
import com.cgr.lesson.exception.code.BaseResponseCode;
import com.cgr.lesson.mapper.PeopleAssessMapper;
import com.cgr.lesson.service.PeopleAssessService;
import com.cgr.lesson.utils.PageUtil;
import com.cgr.lesson.vo.req.PeopleAssessPageReqVO;
import com.cgr.lesson.vo.req.PeopleAssessReqVO;
import com.cgr.lesson.vo.resp.PageVO;
import com.cgr.lesson.vo.resp.PeopleAssessGradeRespVO;
import com.cgr.lesson.vo.resp.PeopleAssessRespVO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 14:08 2020-06-10
 * @ Description：
 * @ Modified By：
 */

@Service
public class PeopleAssessServiceImpl implements PeopleAssessService {
    @Autowired
    private PeopleAssessMapper peopleAssessMapper;
    @Override
    public PeopleAssess peopleAssessAdd(PeopleAssessReqVO vo,String userId) {
        PeopleAssess peopleAssess=new PeopleAssess();
        BeanUtils.copyProperties(vo,peopleAssess);
        peopleAssess.setId(UUID.randomUUID().toString());
        peopleAssess.setCreateId(userId);
        peopleAssess.setCreateTime(new Date());
        int i=peopleAssessMapper.insertSelective(peopleAssess);
        if(i!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        return peopleAssess;
    }

    @Override
    public PeopleAssessGradeRespVO getGradeById(String assessId) {
        return peopleAssessMapper.getGradeByAssessId(assessId);
    }

    @Override
    public PageVO<PeopleAssessRespVO> pageInfo(PeopleAssessPageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<PeopleAssessRespVO> list=peopleAssessMapper.pageInfo(vo);
        return PageUtil.getPageVO(list);
    }
}
