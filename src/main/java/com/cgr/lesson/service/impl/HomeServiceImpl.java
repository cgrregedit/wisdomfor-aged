package com.cgr.lesson.service.impl;

import com.alibaba.fastjson.JSON;
import com.cgr.lesson.entity.SysUser;
import com.cgr.lesson.mapper.SysUserMapper;
import com.cgr.lesson.service.HomeService;
import com.cgr.lesson.service.PermissionService;
import com.cgr.lesson.vo.resp.HomeRespVO;
import com.cgr.lesson.vo.resp.PermissionRespNode;
import com.cgr.lesson.vo.resp.UserInfoRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Author     ：cgr
 * @ Date       ：Created in 15:00 2020-05-12
 * @ Description：
 * @ Modified By：
 */
@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private PermissionService permissionService;

    @Override
    public HomeRespVO getHomeInfo(String userID) {
        HomeRespVO homeRespVO = new HomeRespVO();
        /**
             * mock 导航菜单数据后期直接从DB获取
             */
       // String home = "[{\"children\":[{\"children\":[{\"children\":[{\"children\":[{\"children\": [],\"id\":\"6\",\"title\":\"五级类目5-6\",\"url\":\"string\"}],\"id\":\"5\",\"title\":\"四级类目4- 5\",\"url\":\"string\"}],\"id\":\"4\",\"title\":\"三级类目3- 4\",\"url\":\"string\"}],\"id\":\"3\",\"title\":\"二级类目2- 3\",\"url\":\"string\"}],\"id\":\"1\",\"title\":\"类目1\",\"url\":\"string\"},{\"children\": [],\"id\":\"2\",\"title\":\"类目2\",\"url\":\"string\"}]";
        //List<PermissionRespNode> list = JSON.parseArray(home, PermissionRespNode.class);
        List<PermissionRespNode> list =permissionService.permissionTreeList(userID);
        homeRespVO.setMenus(list);
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userID);
        UserInfoRespVO respVO = new UserInfoRespVO();
        if (sysUser != null) {
            respVO.setUsername(sysUser.getUsername());
            respVO.setDeptName("养老院");
            respVO.setId(sysUser.getId());
        }
        homeRespVO.setUserInfo(respVO);
        return homeRespVO;
    }
}
