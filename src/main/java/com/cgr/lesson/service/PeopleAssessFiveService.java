package com.cgr.lesson.service;

import com.cgr.lesson.entity.PeopleAssessFive;
import com.cgr.lesson.vo.req.PeopleAssessFiveReqVO;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 21:22 2020-06-10
 * @ Description：长者能力
 * @ Modified By：
 */
public interface PeopleAssessFiveService {
    PeopleAssessFive peopleAssessFiveAdd(PeopleAssessFiveReqVO vo,String userId);
}
