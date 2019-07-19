package com.hushuai.fast.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.hushuai.fast.dto.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);

    List<SysUserRole> selectByUid(@Param("uid")Integer uid);




}