package com.cgr.lesson.service;

import com.cgr.lesson.entity.PeopleAssessThree;
import com.cgr.lesson.vo.req.PeopleAssessThreeAddReqVO;

public interface PeopleAssessThreeService {
    PeopleAssessThree peopleAssessThreeAdd(PeopleAssessThreeAddReqVO vo,String userId);
}
