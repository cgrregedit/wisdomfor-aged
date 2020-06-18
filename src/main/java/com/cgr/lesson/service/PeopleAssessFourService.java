package com.cgr.lesson.service;

import com.cgr.lesson.entity.PeopleAssessFour;
import com.cgr.lesson.vo.req.PeopleAssessFourAddReqVO;

public interface PeopleAssessFourService {
    //社会参与评估表新增
    PeopleAssessFour peopleAssessFourAdd(PeopleAssessFourAddReqVO vo,String userId);
}
