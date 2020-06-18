package com.cgr.lesson.service.impl;

import com.github.pagehelper.PageHelper;
import com.cgr.lesson.entity.SysLog;
import com.cgr.lesson.exception.BusinessException;
import com.cgr.lesson.exception.code.BaseResponseCode;
import com.cgr.lesson.mapper.SysLogMapper;
import com.cgr.lesson.service.LogService;
import com.cgr.lesson.utils.PageUtil;
import com.cgr.lesson.vo.req.SysLogPageReqVO;
import com.cgr.lesson.vo.resp.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: LogServiceImpl
 * TODO:日志
 * @Author:
 * @UpdateUser:
 * @Version: 0.0.1
 */
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private SysLogMapper sysLogMapper;
    @Override
    public PageVO<SysLog> pageInfo(SysLogPageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        return PageUtil.getPageVO(sysLogMapper.selectAll(vo));
    }

    @Override
    public void deletedLog(List<String> logIds) {
        int i = sysLogMapper.batchDeletedLog(logIds);
        if(i==0){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
    }
}
