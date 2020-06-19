package com.cgr.lesson.service.impl;

import com.cgr.lesson.entity.PeoplePackage;
import com.cgr.lesson.exception.BusinessException;
import com.cgr.lesson.exception.code.BaseResponseCode;
import com.cgr.lesson.mapper.PeoplePackageMapper;
import com.cgr.lesson.service.PeoplePackageService;
import com.cgr.lesson.utils.PageUtil;
import com.cgr.lesson.vo.req.PeoplePackageAddReqVO;
import com.cgr.lesson.vo.req.PeoplePackagePageReqVO;
import com.cgr.lesson.vo.req.PeoplePackageUpdateReqVO;
import com.cgr.lesson.vo.resp.PageVO;
import com.cgr.lesson.vo.resp.PeoplePackageResqVO;
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
 * @ Date       ：Created in 14:43 2020-06-16
 * @ Description：套餐生成
 * @ Modified By：
 */

@Service
@Slf4j
public class PeoplePackageServiceImpl implements PeoplePackageService {
    @Autowired
    private PeoplePackageMapper peoplePackageMapper;

    //分页查询
    @Override
    public PageVO<PeoplePackageResqVO> selectAllPackage(PeoplePackagePageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
        List<PeoplePackageResqVO> list = peoplePackageMapper.selectAllPackage(vo);
        return PageUtil.getPageVO(list);
    }

    //新增
    @Override
    public PeoplePackage peoplePackageAdd(PeoplePackageAddReqVO vo, String userId) {
        PeoplePackage peoplePackage = new PeoplePackage();
        //查询该个人档案下是否有套餐
        PeoplePackage p1 = peoplePackageMapper.selectByPid(vo.getPid());
        if (p1 != null && p1.getId() != "") {
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        BeanUtils.copyProperties(vo, peoplePackage);
        peoplePackage.setId(UUID.randomUUID().toString());
        peoplePackage.setCreateId(userId);
        peoplePackage.setCreateTime(new Date());
        int i = peoplePackageMapper.insertSelective(peoplePackage);
        if (i != 1) {
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        return peoplePackage;
    }

    @Override
    public PeoplePackage peoplePackageUpdate(PeoplePackageUpdateReqVO vo, String userId) {
        //检查是否存在数据
        PeoplePackage peoplePackage=peoplePackageMapper.selectByPrimaryKey(vo.getId());
        if (null == peoplePackage) {
            log.error("传入的id：{}不合法", vo.getId());
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        BeanUtils.copyProperties(vo,peoplePackage);
        peoplePackage.setUpdateId(userId);
        peoplePackage.setUpdateTime(new Date());
        int i=peoplePackageMapper.updateByPrimaryKeySelective(peoplePackage);
        if (i!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        return peoplePackage;
    }
}
