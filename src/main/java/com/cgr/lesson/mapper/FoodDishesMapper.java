package com.cgr.lesson.mapper;

import com.cgr.lesson.entity.FoodDishes;

public interface FoodDishesMapper {
    int deleteByPrimaryKey(String id);

    int insert(FoodDishes record);

    int insertSelective(FoodDishes record);

    FoodDishes selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FoodDishes record);

    int updateByPrimaryKey(FoodDishes record);
}