package com.cgr.lesson.service.impl;

import com.cgr.lesson.entity.PeoplePastHistory;
import com.cgr.lesson.exception.BusinessException;
import com.cgr.lesson.exception.code.BaseResponseCode;
import com.cgr.lesson.mapper.PeoplePastHistoryMapper;
import com.cgr.lesson.service.PeoplePastHistoryService;
import com.cgr.lesson.vo.req.PeoplePastHistoryAddReqVO;
import com.cgr.lesson.vo.req.PeoplePastHistoryUpdateReqVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 21:42 2020-05-27
 * @ Description：
 * @ Modified By：
 */

@Service
@Slf4j
public class PeoplePastHistoryServiceImpl implements PeoplePastHistoryService {
    @Autowired
    private PeoplePastHistoryMapper peoplePastHistoryMapper;

    @Override
    public PeoplePastHistory PeoplePastHistoryAdd(PeoplePastHistoryAddReqVO vo, String userId) {
        PeoplePastHistory peoplePastHistory=new PeoplePastHistory();
        BeanUtils.copyProperties(vo,peoplePastHistory);
        peoplePastHistory.setId(UUID.randomUUID().toString());
        peoplePastHistory.setCreateId(userId);
        peoplePastHistory.setCreateTime(new Date());
        peoplePastHistory.setDeleted(1);
        int i=peoplePastHistoryMapper.insertSelective(peoplePastHistory);
        if (i!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        return peoplePastHistory;
    }

    @Override
    public void PeoplePastHistoryUpdate(PeoplePastHistoryUpdateReqVO vo, String userId) {
        PeoplePastHistory peoplePastHistory=peoplePastHistoryMapper.selectByPrimaryKey(vo.getId());
        if (null==peoplePastHistory){
            log.error("传入的id：{}不合法",vo.getId());
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        BeanUtils.copyProperties(vo,peoplePastHistory);
        peoplePastHistory.setUpdateId(userId);
        peoplePastHistory.setUpdateTime(new Date());
        int i=peoplePastHistoryMapper.updateByPrimaryKeySelective(peoplePastHistory);
        if (i!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
    }

    @Override
    public PeoplePastHistory getPastHistoryByPid(String pid) {
        return peoplePastHistoryMapper.getPastHistoryByPid(pid);
    }
}
