package com.hushuai.fast.service;

import com.hushuai.fast.dao.SysPermissionMapper;
import com.hushuai.fast.dto.SysPermission;
import com.hushuai.fast.dto.SysRole;
import com.hushuai.fast.dto.SysUser;
import com.hushuai.fast.dto.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: shuaihu2
 * @Date: 2019/7/18
 * @Interface: MyUserDetailsService
 * @Description:
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysPermissionService sysPermissionService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleService sysRoleService;


    /**
     * @Description: 实现父类中loadUserByUsername方法，并且把对应username用户的权限放进去
     * @params: [username]
     * @return: org.springframework.security.core.userdetails.UserDetails
     * @exception:
     * @methodName: loadUserByUsername
     * @updateDate: 2019/7/18 15:04
     * @updateAuthor: shuaihu2
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 数据库中获取对应username的用户实体
        SysUser sysUser = sysUserService.selectByUsername(username);
        if (sysUser != null) {
            // 查询该用户的所有角色
            List<SysUserRole> sysUserRoles = sysUserRoleService.selectByUid(sysUser.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (SysUserRole sysUserRole : sysUserRoles) {
                //codeTODO 2019/7/19 11:02 : 当前是将对应用户的角色名字存储进去 后期应该添加权限
                SysRole sysRole = sysRoleService.selectByPrimaryKey(sysUserRole.getRid());
                grantedAuthorities.add(new SimpleGrantedAuthority(sysRole.getRolename()));
            }
            return new User(sysUser.getUsername(), sysUser.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException(username + " do not exist!");
        }
    }
}
