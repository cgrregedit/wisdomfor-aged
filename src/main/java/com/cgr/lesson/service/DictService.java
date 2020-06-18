package com.cgr.lesson.service;

import com.cgr.lesson.entity.SysDict;
import com.cgr.lesson.vo.req.DictPageReqVO;
import com.cgr.lesson.vo.resp.PageVO;

import java.util.List;
import java.util.Map;


/**
* @Description: 数字字典
* @Param:
* @return:
* @Author:cgr
* @Date: 2020-05-25
*/
public interface DictService {
    //字典表查询
    List<Map<String,String>> selectDict(Integer typeid);

    //分页查询
    PageVO<SysDict> selectAll(DictPageReqVO vo);
}
