package com.briup.crm.web;

import com.briup.crm.web.filter.LoginFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author kj
 * @Date 2020/3/28 19:16
 * @Version 1.0
 */
@Configuration
public class MyWebMvc implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginFilter())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login","pages/login.html", "/css/**", "/images/**", "/js/**", "layui/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("pages/login.html");
    }
}
