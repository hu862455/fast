package com.hushuai.fast.service;
import java.util.List;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.hushuai.fast.dao.SysRoleMapper;
import com.hushuai.fast.dto.SysRole;
@Service
public class SysRoleService{

    @Resource
    private SysRoleMapper sysRoleMapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return sysRoleMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(SysRole record) {
        return sysRoleMapper.insert(record);
    }

    
    public int insertSelective(SysRole record) {
        return sysRoleMapper.insertSelective(record);
    }

    
    public SysRole selectByPrimaryKey(Integer id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(SysRole record) {
        return sysRoleMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(SysRole record) {
        return sysRoleMapper.updateByPrimaryKey(record);
    }

	public List<SysRole> selectByRolename(String rolename){
		 return sysRoleMapper.selectByRolename(rolename);
	}




}
