package com.cgr.lesson.service;

import com.cgr.lesson.entity.PeopleAssessFirst;
import com.cgr.lesson.vo.req.PeopleAssessFirstAddReqVO;

public interface PeopleAssessFirstService {
    //评估一新增
    PeopleAssessFirst peopleAssessFirstAdd(PeopleAssessFirstAddReqVO vo,String userId);
}
