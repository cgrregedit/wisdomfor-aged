package com.cgr.lesson.mapper;

import com.cgr.lesson.entity.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SysDeptMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);

    //查询所有
    List<SysDept> selectAll();

    //维护新的层级关系
    int updateRelationCode(@Param("oldStr") String oldStr, @Param("newStr") String newStr, @Param("relationCode") String relationCode);

    //通过层级关系查找所有叶子节点
    List<String> selectChildIds(String relationCode);

    //删除它和它所有的子集节点
    int deletedDepts(@Param("updateTime") Date updateTime, @Param("list") List<String> list);

}