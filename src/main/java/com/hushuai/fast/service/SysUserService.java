package com.hushuai.fast.service;

import com.hushuai.fast.dao.SysPermissionMapper;
import com.hushuai.fast.dao.SysUserMapper;
import com.hushuai.fast.dto.SysUser;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    public int deleteByPrimaryKey(Integer id) {
        return sysUserMapper.deleteByPrimaryKey(id);
    }


    public int insert(SysUser record) {
        return sysUserMapper.insert(record);
    }


    public int insertSelective(SysUser record) {
        return sysUserMapper.insertSelective(record);
    }


    public SysUser selectByPrimaryKey(Integer id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }


    public int updateByPrimaryKeySelective(SysUser record) {
        return sysUserMapper.updateByPrimaryKeySelective(record);
    }


    public int updateByPrimaryKey(SysUser record) {
        return sysUserMapper.updateByPrimaryKey(record);
    }

    public SysUser selectByUsername(String username) {
        return sysUserMapper.selectByUsername(username).get(0);
    }

    public SysUser getCurUser(){
        String curName = SecurityContextHolder.getContext().getAuthentication().getName();
        SysUser curUser = this.selectByUsername(curName);
        return curUser;
    }
}
