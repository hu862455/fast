package com.hushuai.fast.config;

import com.hushuai.fast.dto.SysPermission;
import com.hushuai.fast.service.SysPermissionService;
import com.hushuai.fast.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: shuaihu2
 * @Date: 2019/7/19
 * @Interface: MyPermissionEvaluator
 * @Description: 实现PermissionEvaluator 自定义注解@PreAuthorize相关逻辑
 */
@Component
public class MyPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private SysPermissionService sysPermissionService;

    @Autowired
    private SysUserService sysUserService;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object targetPermission) {
        // 获得loadUserByUsername()方法的结果
        User user = (User) authentication.getPrincipal();
        // 获取当前用户所有权限
        List<SysPermission> permissions = sysPermissionService.findAllPermissionByUid(
                sysUserService.selectByUsername(user.getUsername()).getId());
        for (SysPermission permission : permissions) {
            if (permission.getUrl().equals(targetUrl) && permission.getPermissionname().equals(targetPermission)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
