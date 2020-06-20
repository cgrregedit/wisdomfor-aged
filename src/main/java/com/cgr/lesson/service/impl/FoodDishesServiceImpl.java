package com.cgr.lesson.service.impl;

import com.cgr.lesson.entity.FoodDishes;
import com.cgr.lesson.exception.BusinessException;
import com.cgr.lesson.exception.code.BaseResponseCode;
import com.cgr.lesson.mapper.FoodDishesMapper;
import com.cgr.lesson.service.FoodDishesService;
import com.cgr.lesson.utils.PageUtil;
import com.cgr.lesson.vo.req.FoodDishesAddReqVO;
import com.cgr.lesson.vo.req.FoodDishesPageReqVO;
import com.cgr.lesson.vo.req.FoodDishesUpdateVO;
import com.cgr.lesson.vo.resp.FoodDishesRespVO;
import com.cgr.lesson.vo.resp.PageVO;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 15:54 2020-06-20
 * @ Description：菜品设置
 * @ Modified By：
 */

@Service
@Slf4j
public class FoodDishesServiceImpl implements FoodDishesService {
    @Autowired
    private FoodDishesMapper foodDishesMapper;

    //新增
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

    //分页查询
    @Override
    public PageVO<FoodDishesRespVO> pageInfo(FoodDishesPageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<FoodDishesRespVO> list=foodDishesMapper.selectAll(vo);
        return PageUtil.getPageVO(list);
    }

    //编辑
    @Override
    public FoodDishes foodDishesUpdate(FoodDishesUpdateVO vo, String userId) {
        //检查数据
        FoodDishes foodDishes=foodDishesMapper.selectByPrimaryKey(vo.getId());
        if(null==foodDishes){
            log.error("传入的ID："+vo.getId()+"错误");
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        BeanUtils.copyProperties(vo,foodDishes);
        foodDishes.setUpdateId(userId);
        foodDishes.setUpdateTime(new Date());
        int i=foodDishesMapper.updateByPrimaryKeySelective(foodDishes);
        if(i!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        return foodDishes;
    }

    //删除
    @Override
    public void foodDishesDelete(String id) {
        FoodDishes foodDishes=foodDishesMapper.selectByPrimaryKey(id);
        if(null==foodDishes){
            log.error("传入的ID："+id+"错误");
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        foodDishes.setDeleted(0);
        int i=foodDishesMapper.updateByPrimaryKeySelective(foodDishes);
        if(i!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
    }
}
