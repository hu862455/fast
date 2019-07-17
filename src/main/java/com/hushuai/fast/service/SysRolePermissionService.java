package com.hushuai.fast.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.hushuai.fast.dao.SysRolePermissionMapper;
import com.hushuai.fast.dto.SysRolePermission;
@Service
public class SysRolePermissionService{

    @Resource
    private SysRolePermissionMapper sysRolePermissionMapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return sysRolePermissionMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(SysRolePermission record) {
        return sysRolePermissionMapper.insert(record);
    }

    
    public int insertSelective(SysRolePermission record) {
        return sysRolePermissionMapper.insertSelective(record);
    }

    
    public SysRolePermission selectByPrimaryKey(Integer id) {
        return sysRolePermissionMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(SysRolePermission record) {
        return sysRolePermissionMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(SysRolePermission record) {
        return sysRolePermissionMapper.updateByPrimaryKey(record);
    }

}
