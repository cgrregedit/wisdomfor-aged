package com.cgr.lesson.service;

import com.cgr.lesson.vo.req.FoodRecipesAddReqVO;
import com.cgr.lesson.vo.resp.FoodRecipesRespVO;

import java.util.List;

public interface FoodRecipesService {
    //批量插入
    void batchInsert(FoodRecipesAddReqVO vo);

    //根据星期几查询菜谱
    List<FoodRecipesRespVO> selectByWeek(String week);
}
