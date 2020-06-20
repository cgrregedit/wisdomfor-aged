package com.cgr.lesson.service;

import com.cgr.lesson.entity.FoodDishes;
import com.cgr.lesson.vo.req.FoodDishesAddReqVO;
import com.cgr.lesson.vo.req.FoodDishesPageReqVO;
import com.cgr.lesson.vo.req.FoodDishesUpdateVO;
import com.cgr.lesson.vo.resp.FoodDishesRespVO;
import com.cgr.lesson.vo.resp.PageVO;

public interface FoodDishesService {
    //菜品增加
    FoodDishes foodDishesAdd(FoodDishesAddReqVO vo,String userId);

    //分页查询
    PageVO<FoodDishesRespVO> pageInfo(FoodDishesPageReqVO vo);

    //编辑
    FoodDishes foodDishesUpdate(FoodDishesUpdateVO vo,String userId);

    //删除
    void foodDishesDelete(String id);
}
