package com.cgr.lesson.mapper;

import com.cgr.lesson.entity.SysDict;
import com.cgr.lesson.vo.req.DictPageReqVO;

import java.util.List;
import java.util.Map;

public interface SysDictMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysDict record);

    int insertSelective(SysDict record);

    SysDict selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysDict record);

    int updateByPrimaryKey(SysDict record);

    //根据typei的查询数据字典
    List<SysDict> selectDict(Integer typeid);

    //分页查询数字字典
    List<SysDict> selectAll(DictPageReqVO vo);
}