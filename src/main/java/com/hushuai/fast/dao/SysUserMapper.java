package com.hushuai.fast.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.hushuai.fast.dto.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    List<SysUser> findByUsername(@Param("username") String username);

}