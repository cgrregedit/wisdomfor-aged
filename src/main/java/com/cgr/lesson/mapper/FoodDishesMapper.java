package com.cgr.lesson.mapper;

import com.cgr.lesson.entity.FoodDishes;
import com.cgr.lesson.vo.req.FoodDishesPageReqVO;
import com.cgr.lesson.vo.resp.FoodDishesRespVO;

import java.util.List;

public interface FoodDishesMapper {
    int deleteByPrimaryKey(String id);

    int insert(FoodDishes record);

    int insertSelective(FoodDishes record);

    FoodDishes selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FoodDishes record);

    int updateByPrimaryKey(FoodDishes record);

    //分页查询菜品信息
    List<FoodDishesRespVO> selectAll(FoodDishesPageReqVO vo);
}