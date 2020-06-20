package com.cgr.lesson.service.impl;

import com.cgr.lesson.entity.FoodDishes;
import com.cgr.lesson.exception.BusinessException;
import com.cgr.lesson.exception.code.BaseResponseCode;
import com.cgr.lesson.mapper.FoodDishesMapper;
import com.cgr.lesson.service.FoodDishesService;
import com.cgr.lesson.vo.req.FoodDishesAddReqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 15:54 2020-06-20
 * @ Description：菜品设置
 * @ Modified By：
 */

@Service
public class FoodDishesServiceImpl implements FoodDishesService {
    @Autowired
    private FoodDishesMapper foodDishesMapper;

    @Override
    public FoodDishes foodDishesAdd(FoodDishesAddReqVO vo, String userId) {
        FoodDishes foodDishes=new FoodDishes();
        BeanUtils.copyProperties(vo,foodDishes);
        foodDishes.setId(UUID.randomUUID().toString());
        foodDishes.setCreateId(userId);
        foodDishes.setCreateTime(new Date());
        int i=foodDishesMapper.insertSelective(foodDishes);
        if(i!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        return foodDishes;
    }
}
