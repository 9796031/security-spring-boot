package com.home.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author liqingdong
 * spring-security配置类
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 查询用户信息
     * 测试默认配置时注释MyUserDetailsService
     * @return
     */
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("zhangsan").password("123").authorities("p1").build());
//        manager.createUser(User.withUsername("lisi").password("123").authorities("p2").build());
//        return manager;
//    }

    // 密码编码器 原始密码校验, 不加密方式校验
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 配置安全拦截机制
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf().disable()
                // FIXME 自定义登录页面时需要禁用security的csrf控制, security不再限制CSRF
                .authorizeRequests()
                // 访问/r/1时需要有p1权限
                .antMatchers("/r/1").hasAuthority("p1")
                // 访问/r/2时需要有p2权限
                .antMatchers("/r/2").hasAuthority("p2")
                // /r/** 需要认证通过
                .antMatchers("/r/**").authenticated()
                //其他可以放行
                .anyRequest().permitAll()
                // 允许表单登录
                .and().formLogin()
                // FIXME 自定义登录页面配置
                .loginPage("/login-view")
                // FIXME 自定义登录页面处理URL
                .loginProcessingUrl("/login")
                // 自定义登录成功的页面地址
                .defaultSuccessUrl("/login-success")
                // session配置, 创建新session
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                // 自定义退出, 会调用logoutURL, 退出成功后重定向到登录页面, 退出时会情况session
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login-view?logout");
    }
}
