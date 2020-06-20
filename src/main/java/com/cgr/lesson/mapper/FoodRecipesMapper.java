package com.cgr.lesson.mapper;

import com.cgr.lesson.entity.FoodRecipes;
import com.cgr.lesson.vo.resp.FoodRecipesRespVO;

import java.util.List;

public interface FoodRecipesMapper {
    int deleteByPrimaryKey(String id);

    int insert(FoodRecipes record);

    int insertSelective(FoodRecipes record);

    FoodRecipes selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FoodRecipes record);

    int updateByPrimaryKey(FoodRecipes record);

    //批量插入
    int BatchInsert(List<FoodRecipes> list);

    //根据星期几删除菜谱
    int deleteByWeek(String week);

    //根据星期几查询菜谱
    List<FoodRecipesRespVO> selectByweek(String week);

}