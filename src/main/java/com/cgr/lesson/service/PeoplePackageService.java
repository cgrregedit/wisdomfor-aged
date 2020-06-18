package com.cgr.lesson.service;

import com.cgr.lesson.entity.PeoplePackage;
import com.cgr.lesson.vo.req.PeoplePackageAddReqVO;
import com.cgr.lesson.vo.req.PeoplePackagePageReqVO;
import com.cgr.lesson.vo.req.PeoplePackageUpdateReqVO;
import com.cgr.lesson.vo.resp.PageVO;
import com.cgr.lesson.vo.resp.PeoplePackageResqVO;

public interface PeoplePackageService {
    //分页查询套餐生成信息
    PageVO<PeoplePackageResqVO> selectAllPackage(PeoplePackagePageReqVO vo);

    //新增
    PeoplePackage peoplePackageAdd(PeoplePackageAddReqVO vo,String userId);

    //编辑
    PeoplePackage peoplePackageUpdate(PeoplePackageUpdateReqVO vo,String userId);
}
