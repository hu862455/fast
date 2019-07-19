package com.hushuai.fast.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.hushuai.fast.dto.SysRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRole> selectByRolename(@Param("rolename")String rolename);

}