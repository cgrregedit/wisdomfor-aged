package com.cgr.lesson;

import com.cgr.lesson.config.CrossOriginfilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@MapperScan("com.cgr.lesson.mapper")
public class WisdomforAgedApplication {

    public static void main(String[] args) {
        SpringApplication.run(WisdomforAgedApplication.class, args);
    }

    @Bean
    public CrossOriginfilter crossOriginfilter(){
        return new CrossOriginfilter();
    }
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setName("crossOriginfilter");
        filterRegistrationBean.setFilter(crossOriginfilter());
        filterRegistrationBean.addUrlPatterns("/api/*");
        return filterRegistrationBean;
    }

}
