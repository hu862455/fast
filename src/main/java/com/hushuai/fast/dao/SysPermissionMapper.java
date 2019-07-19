package com.hushuai.fast.dao;
import org.apache.ibatis.annotations.Param;

import com.hushuai.fast.dto.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);

    List<SysPermission> findAllPermissionByUid(Integer id);

    List<SysPermission> findALL();

}
