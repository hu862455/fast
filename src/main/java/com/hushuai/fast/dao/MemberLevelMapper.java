package com.hushuai.fast.dao;

import com.hushuai.fast.dto.MemberLevel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberLevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberLevel record);

    int insertSelective(MemberLevel record);

    MemberLevel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberLevel record);

    int updateByPrimaryKey(MemberLevel record);
}