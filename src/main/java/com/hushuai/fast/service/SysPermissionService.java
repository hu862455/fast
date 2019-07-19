package com.hushuai.fast.service;
import java.util.List;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.hushuai.fast.dao.SysPermissionMapper;
import com.hushuai.fast.dto.SysPermission;
@Service
public class SysPermissionService{

    @Resource
    private SysPermissionMapper sysPermissionMapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return sysPermissionMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(SysPermission record) {
        return sysPermissionMapper.insert(record);
    }

    
    public int insertSelective(SysPermission record) {
        return sysPermissionMapper.insertSelective(record);
    }

    
    public SysPermission selectByPrimaryKey(Integer id) {
        return sysPermissionMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(SysPermission record) {
        return sysPermissionMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(SysPermission record) {
        return sysPermissionMapper.updateByPrimaryKey(record);
    }

	public List<SysPermission> findALL(){
		 return sysPermissionMapper.findALL();
	}

    public List<SysPermission> findAllPermissionByUid(Integer id) {
        return sysPermissionMapper.findAllPermissionByUid(id);
    }
}
