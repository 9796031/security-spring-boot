1. 自定义加载用户MyUserDetailsService
2. BCryptTest测试BCrypt加密  
3. 配置BCrypt方式验证密码
4. FIXME标注的是配置自定义登录页面时需要修改的东西
5. 从数据库拉取用户信息认证
com.home.service.MyUserDetailsService.loadUserByUsername
6. 获取登录用户信息  
com.home.controller.LoginController.getUserInfo
7. session(会话)配置
```.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);```
8. 退出登录
```.and().logout().logoutUrl("/logout").logoutSuccessUrl("/login-view?logout");```
9. 通过数据库加载权限
com.home.service.MyUserDetailsService.loadUserByUsername  
com.home.dao.UserDao.getPermissionByUserName  
# security-spring-boot
