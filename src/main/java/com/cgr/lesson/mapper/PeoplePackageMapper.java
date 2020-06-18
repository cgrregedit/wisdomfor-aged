package com.cgr.lesson.mapper;

import com.cgr.lesson.entity.PeoplePackage;
import com.cgr.lesson.vo.req.PeoplePackagePageReqVO;
import com.cgr.lesson.vo.resp.PeoplePackageResqVO;

import java.util.List;

public interface PeoplePackageMapper {
    int deleteByPrimaryKey(String id);

    int insert(PeoplePackage record);

    int insertSelective(PeoplePackage record);

    PeoplePackage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PeoplePackage record);

    int updateByPrimaryKey(PeoplePackage record);

    //分页查询套餐生成信息
    List<PeoplePackageResqVO> selectAllPackage(PeoplePackagePageReqVO vo);

    //根据个人ID查询套餐
    PeoplePackage selectByPid(String pid);
}