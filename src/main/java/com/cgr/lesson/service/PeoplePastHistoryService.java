package com.cgr.lesson.service;

import com.cgr.lesson.entity.PeoplePastHistory;
import com.cgr.lesson.vo.req.PeoplePastHistoryAddReqVO;
import com.cgr.lesson.vo.req.PeoplePastHistoryUpdateReqVO;

public interface PeoplePastHistoryService {
    //既往史新增
    PeoplePastHistory PeoplePastHistoryAdd(PeoplePastHistoryAddReqVO vo,String userId);
    //既往史编辑
    void PeoplePastHistoryUpdate(PeoplePastHistoryUpdateReqVO vo,String userId);
    //根据pId获取既往史
    PeoplePastHistory getPastHistoryByPid(String pid);
}
