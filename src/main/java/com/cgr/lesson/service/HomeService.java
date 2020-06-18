package com.cgr.lesson.service;


import com.cgr.lesson.vo.resp.HomeRespVO;

/**
* @Description:  首页导航栏
* @Param:
* @return:
* @Author:cgr
* @Date: 2020-05-12
*/
public interface HomeService {
    HomeRespVO getHomeInfo(String userID);
}
