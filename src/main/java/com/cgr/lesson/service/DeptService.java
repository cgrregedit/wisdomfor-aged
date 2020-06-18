package com.cgr.lesson.service;

import com.cgr.lesson.entity.SysDept;
import com.cgr.lesson.vo.req.DeptAddReqVO;
import com.cgr.lesson.vo.req.DeptUpdateReqVO;
import com.cgr.lesson.vo.resp.DeptRespNodeVO;

import java.util.List;

public interface DeptService {
    List<SysDept> selectAll();

    List<DeptRespNodeVO> deptTreeList(String deptId);

    //新增科室
    SysDept addDept(DeptAddReqVO vo);

    //编辑科室
    void updateDept(DeptUpdateReqVO vo);

    //删除科室
    void deleted(String id);
}
