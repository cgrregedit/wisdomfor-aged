package com.cgr.lesson.service;

import com.cgr.lesson.entity.PeopleAssessSecond;
import com.cgr.lesson.vo.req.PeopleAssessSecondAddReqVO;

public interface PeopleAssessSecondService {
    //精神状态评估表新增
    PeopleAssessSecond peopleAssessSecondAdd(PeopleAssessSecondAddReqVO vo,String userId);
}
