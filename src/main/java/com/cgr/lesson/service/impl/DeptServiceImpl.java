package com.cgr.lesson.service.impl;

import com.cgr.lesson.constants.Constant;
import com.cgr.lesson.entity.SysDept;
import com.cgr.lesson.entity.SysUser;
import com.cgr.lesson.exception.BusinessException;
import com.cgr.lesson.exception.code.BaseResponseCode;
import com.cgr.lesson.mapper.SysDeptMapper;
import com.cgr.lesson.mapper.SysUserMapper;
import com.cgr.lesson.service.DeptService;
import com.cgr.lesson.service.RedisService;
import com.cgr.lesson.utils.CodeUtil;
import com.cgr.lesson.vo.req.DeptAddReqVO;
import com.cgr.lesson.vo.req.DeptUpdateReqVO;
import com.cgr.lesson.vo.resp.DeptRespNodeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 21:59 2020-05-17
 * @ Description：科室信息
 * @ Modified By：
 */

@Service
@Slf4j
public class DeptServiceImpl implements DeptService {
    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Autowired
    private RedisService redisService;
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysDept> selectAll() {
        List<SysDept> list = sysDeptMapper.selectAll();
        for (SysDept sysDept : list) {
            SysDept parent = sysDeptMapper.selectByPrimaryKey(sysDept.getPid());
            if (parent != null) {
                sysDept.setPidName(sysDept.getName());
            }
        }
        return list;
    }

    //查询部门表所有数据递归封装成树形结构
    @Override
    public List<DeptRespNodeVO> deptTreeList(String deptId) {
        List<SysDept> list = sysDeptMapper.selectAll();
        if (!StringUtils.isEmpty(deptId) && !list.isEmpty()) {
            for (SysDept s : list) {
                if (s.getId().equals(deptId)) {
                    list.remove(s);
                    break;
                }
            }
        }
        //默认加一个顶级方便新增顶级部门
        DeptRespNodeVO respNodeVO = new DeptRespNodeVO();
        respNodeVO.setTitle("默认顶级部门");
        respNodeVO.setId("0");
        respNodeVO.setSpread(true);
        respNodeVO.setChildren(getTree(list));
        List<DeptRespNodeVO> result = new ArrayList<>();
        result.add(respNodeVO);
        return result;
    }

    private List<DeptRespNodeVO> getTree(List<SysDept> all) {
        List<DeptRespNodeVO> list = new ArrayList<>();
        for (SysDept s : all) {
            if (s.getPid().equals("0")) {
                DeptRespNodeVO respNodeVO = new DeptRespNodeVO();
                respNodeVO.setId(s.getId());
                respNodeVO.setTitle(s.getName());
                respNodeVO.setChildren(getChild(s.getId(), all));
                list.add(respNodeVO);
            }
        }
        return list;
    }

    private List<DeptRespNodeVO> getChild(String id, List<SysDept> all) {
        List<DeptRespNodeVO> list = new ArrayList<>();
        for (SysDept s :
                all) {
            if (s.getPid().equals(id)) {
                DeptRespNodeVO deptRespNodeVO = new DeptRespNodeVO();
                deptRespNodeVO.setId(s.getId());
                deptRespNodeVO.setTitle(s.getName());
                deptRespNodeVO.setChildren(getChild(s.getId(), all));
                list.add(deptRespNodeVO);

            }
        }
        return list;
    }

    @Override
    public SysDept addDept(DeptAddReqVO vo) {
        String relationCode;
        long result = redisService.incrby(Constant.DEPT_CODE_KEY, 1);
        String deptCode = CodeUtil.deptCode(String.valueOf(result), 6, "0");
        SysDept parent = sysDeptMapper.selectByPrimaryKey(vo.getPid());
        if (vo.getPid().equals("0")) {
            relationCode = deptCode;
        } else if (null == parent) {
            log.error("传入的 pid:{}不合法", vo.getPid());
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        } else {
            relationCode = parent.getRelationCode() + deptCode;
        }
        SysDept sysDept = new SysDept();
        BeanUtils.copyProperties(vo, sysDept);
        sysDept.setCreateTime(new Date());
        sysDept.setId(UUID.randomUUID().toString());
        sysDept.setDeptNo(deptCode);
        sysDept.setRelationCode(relationCode);
        int count = sysDeptMapper.insertSelective(sysDept);
        if (count != 1) {
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        return sysDept;
    }


    //编辑科室
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateDept(DeptUpdateReqVO vo) {
        //保存更新的部门数据
        SysDept sysDept = sysDeptMapper.selectByPrimaryKey(vo.getId());
        if (null == sysDept) {
            log.error("传入 的 id:{}不合法", vo.getId());
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        SysDept update = new SysDept();
        BeanUtils.copyProperties(vo, update);
        update.setUpdateTime(new Date());
        int count = sysDeptMapper.updateByPrimaryKeySelective(update);
        if (count != 1) {
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        //就是维护层级关系
        if (!vo.getPid().equals(sysDept.getPid())) {
            //子集的部门层级关系编码=父级部门层级关系编码+它本身部门编码
            SysDept newParent = sysDeptMapper.selectByPrimaryKey(vo.getPid());
            if (!vo.getPid().equals("0") && null == newParent) {
                log.info("修改后的部门在数据库查找不到{}", vo.getPid());
                throw new BusinessException(BaseResponseCode.DATA_ERROR);
            }
            SysDept oldParent = sysDeptMapper.selectByPrimaryKey(sysDept.getPid());
            String oleRelation;
            String newRelation;
            /**
             * 根目录挂靠到其它目录
             */
            if (sysDept.getPid().equals("0")) {
                oleRelation = sysDept.getRelationCode();
                newRelation = newParent.getRelationCode() + sysDept.getDeptNo();
            } else if (vo.getPid().equals("0")) {
                oleRelation = sysDept.getRelationCode();
                newRelation = sysDept.getDeptNo();
            } else {
                oleRelation = oldParent.getRelationCode();
                newRelation = newParent.getRelationCode();
            }
            sysDeptMapper.updateRelationCode(oleRelation, newRelation, sysDept.getRelationCode());
        }
    }

    //删除科室
    @Override
    public void deleted(String id) {
        SysDept sysDept = sysDeptMapper.selectByPrimaryKey(id);
        if (null == sysDept) {
            log.error("传入 的 id:{}不合法", id);
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        List<String> deptIds = sysDeptMapper.selectChildIds(sysDept.getRelationCode());
        List<SysUser> list = sysUserMapper.selectUserInfoByDeptIds(deptIds);
        if (!list.isEmpty()) {
            throw new BusinessException(BaseResponseCode.NOT_PERMISSION_DELETED_DEPT);
        }
        sysDept.setDeleted(0);
        sysDept.setUpdateTime(new Date());
        int count = sysDeptMapper.updateByPrimaryKeySelective(sysDept);
        if (count != 1) {
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
    }
}
