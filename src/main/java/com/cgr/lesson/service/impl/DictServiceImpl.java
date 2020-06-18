package com.cgr.lesson.service.impl;

import com.cgr.lesson.entity.SysDict;
import com.cgr.lesson.mapper.SysDictMapper;
import com.cgr.lesson.service.DictService;
import com.cgr.lesson.utils.PageUtil;
import com.cgr.lesson.vo.req.DictPageReqVO;
import com.cgr.lesson.vo.resp.PageVO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 19:47 2020-05-25
 * @ Description： 数字字典
 * @ Modified By：
 */
@Service
public class DictServiceImpl implements DictService {
    @Autowired
    private SysDictMapper sysDictMapper;

    @Override
    public List<Map<String, String>> selectDict(Integer typeid) {
        List<SysDict> list=sysDictMapper.selectDict(typeid);

        if (null == list || list.isEmpty()) {
            return null;
        }
        List<Map<String, String>> mapList = new ArrayList<>();
        Map<String,String> map;
        for (SysDict sysDict : list) {
            map = new HashMap<String, String>();
            map.put("lable",sysDict.getDictname());
            map.put("value",sysDict.getDictid());
            mapList.add(map);
        }
        return mapList;
    }

    @Override
    public PageVO<SysDict> selectAll(DictPageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(),vo.getPageNum());
        List<SysDict> list=sysDictMapper.selectAll(vo);
        return PageUtil.getPageVO(list);
    }
}
