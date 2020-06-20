package com.cgr.lesson.service;

import com.cgr.lesson.entity.FoodDishes;
import com.cgr.lesson.vo.req.FoodDishesAddReqVO;

public interface FoodDishesService {
    //菜品增加
    FoodDishes foodDishesAdd(FoodDishesAddReqVO vo,String userId);
}
