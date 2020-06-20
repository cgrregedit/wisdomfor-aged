package com.cgr.lesson.service.impl;

import com.cgr.lesson.entity.FoodRecipes;
import com.cgr.lesson.exception.BusinessException;
import com.cgr.lesson.exception.code.BaseResponseCode;
import com.cgr.lesson.mapper.FoodRecipesMapper;
import com.cgr.lesson.service.FoodRecipesService;
import com.cgr.lesson.vo.req.FoodRecipesAddReqVO;
import com.cgr.lesson.vo.resp.FoodRecipesRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 22:58 2020-06-20
 * @ Description：菜谱
 * @ Modified By：
 */
@Service
public class FoodRecipesServiceImpl implements FoodRecipesService {
    @Autowired
    private FoodRecipesMapper foodRecipesMapper;

    @Override
    public void batchInsert(FoodRecipesAddReqVO vo) {
       //删除该天的菜谱
        foodRecipesMapper.deleteByWeek(vo.getWeek());
        //判断集合
        if(vo.getFoodIds()==null||vo.getFoodIds().isEmpty()){
            return;
        }
        List<FoodRecipes> list=new ArrayList<>();
        for (String foodId:vo.getFoodIds()){
            FoodRecipes foodRecipes=new FoodRecipes();
            foodRecipes.setId(UUID.randomUUID().toString());
            foodRecipes.setWeek(vo.getWeek());
            foodRecipes.setFoodId(foodId);
            list.add(foodRecipes);
        }
        //批量插入
        int i=foodRecipesMapper.BatchInsert(list);
        if(i==0){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }


    }

    @Override
    public List<FoodRecipesRespVO> selectByWeek(String week) {
        return foodRecipesMapper.selectByweek(week);
    }
}
