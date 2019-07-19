package com.hushuai.fast.service;
import java.util.List;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.hushuai.fast.dto.SysUserRole;
import com.hushuai.fast.dao.SysUserRoleMapper;
@Service
public class SysUserRoleService{

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return sysUserRoleMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(SysUserRole record) {
        return sysUserRoleMapper.insert(record);
    }

    
    public int insertSelective(SysUserRole record) {
        return sysUserRoleMapper.insertSelective(record);
    }

    
    public SysUserRole selectByPrimaryKey(Integer id) {
        return sysUserRoleMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(SysUserRole record) {
        return sysUserRoleMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(SysUserRole record) {
        return sysUserRoleMapper.updateByPrimaryKey(record);
    }

	public List<SysUserRole> selectByUid(Integer uid){
		 return sysUserRoleMapper.selectByUid(uid);
	}




}
