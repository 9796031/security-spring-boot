package com.home.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author liqingdong
 * springmvc配置
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 使用spring-security提供的登录页面
        // registry.addViewController("/").setViewName("redirect:/login");
        // FIXME 使用自定义的登录页面
        registry.addViewController("/").setViewName("redirect:/login-view");
        // FIXME 定位到login视图
        registry.addViewController("/login-view").setViewName("login");
    }
}
