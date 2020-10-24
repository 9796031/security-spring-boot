package com.home.controller;

import com.home.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liqingdong
 */
@RestController
public class LoginController {

    @GetMapping(value = "/login-success", produces = {"text/plain;charset=UTF-8"})
    public String loginSuccess() {
       return getUserInfo() + ": 登录成功";
    }

    @GetMapping("/r/r1")
    public String r1() {
        return "r1";
    }

    @GetMapping("/r/r2")
    public String r2() {
        return "r2";
    }

    /**
     * 获取用户登录信息
     * @return
     */
    private String getUserInfo() {
        // 当前认证通过的用户信息身份
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 用户信息
        Object principal = authentication.getPrincipal();
        // 用户未登录
        if (principal == null) {
            return "";
        }
        if (principal instanceof UserDetails) {
            // 可以获取用户信息
            return ((UserDetails)principal).getUsername();
        }
        return principal.toString();
    }
}
