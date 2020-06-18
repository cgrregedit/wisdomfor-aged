package com.cgr.lesson.service;

import com.cgr.lesson.entity.PeopleLiving;
import com.cgr.lesson.vo.req.PeopleLivingAddReqVO;
import com.cgr.lesson.vo.req.PeopleLivingUpdateReqVO;

public interface PeopleLivingService {
    //生活环境新增
    PeopleLiving peopleLivingAdd(PeopleLivingAddReqVO vo,String userId);

    //生活习惯修改
    void peopleLivingUpdate(PeopleLivingUpdateReqVO vo,String userId);

    //根据pid获取生活环境
    PeopleLiving getLivingByPid(String pid);
}
