package com.cgr.lesson.service.impl;

import com.cgr.lesson.constants.Constant;
import com.cgr.lesson.entity.PeopleBaseInfo;
import com.cgr.lesson.exception.BusinessException;
import com.cgr.lesson.exception.code.BaseResponseCode;
import com.cgr.lesson.mapper.PeopleBaseinfoMapper;
import com.cgr.lesson.service.PeopleBaseInfoService;
import com.cgr.lesson.service.RedisService;
import com.cgr.lesson.utils.CodeUtil;
import com.cgr.lesson.utils.PageUtil;
import com.cgr.lesson.vo.req.PeopleBaseInfoReqVO;
import com.cgr.lesson.vo.req.PeopleBaseInfoUpdateReqVO;
import com.cgr.lesson.vo.req.PeopleInfoPageReqVO;
import com.cgr.lesson.vo.req.TestProReqVO;
import com.cgr.lesson.vo.resp.PageVO;
import com.cgr.lesson.vo.resp.PeopleInfoRespVO;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 19:33 2020-05-26
 * @ Description：信息登记-个人基本信息
 * @ Modified By：
 */
@Service
@Slf4j
public class PeopleBaseInfoServiceImpl implements PeopleBaseInfoService {
    @Autowired
    private PeopleBaseinfoMapper peopleBaseinfoMapper;
    @Autowired
    private RedisService redisService;

    //新增
    @Override
    public PeopleBaseInfo peopleBaseInfoAdd(PeopleBaseInfoReqVO vo,String userId) {
        long result=redisService.incrby(Constant.PEOPELE_CODE_KEY,1);
        String peopleCode= CodeUtil.peopleCode(String.valueOf(result),8,"0");
        PeopleBaseInfo peopleBaseInfo=new PeopleBaseInfo();
        BeanUtils.copyProperties(vo,peopleBaseInfo);
        peopleBaseInfo.setId(UUID.randomUUID().toString());
        peopleBaseInfo.setPeopleId(peopleCode);
        peopleBaseInfo.setCreateId(userId);
        peopleBaseInfo.setCreateTime(new Date());
        int i=peopleBaseinfoMapper.insertSelective(peopleBaseInfo);
        if (i!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        return peopleBaseInfo;
    }

    //修改
    @Override
    public void peopleBaseInfoUpdate(PeopleBaseInfoUpdateReqVO vo, String userId) {
        PeopleBaseInfo peopleBaseInfo=peopleBaseinfoMapper.selectByPrimaryKey(vo.getId());
        if (null==peopleBaseInfo){
            log.error("传入的id:{}不合法",vo.getId());
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        BeanUtils.copyProperties(vo,peopleBaseInfo);
        peopleBaseInfo.setUpdateId(userId);
        peopleBaseInfo.setUpdateTime(new Date());
        int i=peopleBaseinfoMapper.updateByPrimaryKeySelective(peopleBaseInfo);
        if (i!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
    }

    //分页查询基本信息
    @Override
    public PageVO<PeopleInfoRespVO> pageInfo(PeopleInfoPageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<PeopleInfoRespVO> list = peopleBaseinfoMapper.selectAll(vo);
        return PageUtil.getPageVO(list);
    }

    //根据ID获取基本信息
    @Override
    public PeopleBaseInfo getPeopleBaseInfoById(String id) {
        return peopleBaseinfoMapper.selectByPrimaryKey(id);
    }

    //存储过程测试
    @Override
    public TestProReqVO getTestPro(TestProReqVO vo) {
        return peopleBaseinfoMapper.getTestPro(vo);
    }
}
