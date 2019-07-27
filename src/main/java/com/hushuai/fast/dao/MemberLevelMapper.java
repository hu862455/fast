package com.hushuai.fast.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

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

    List<MemberLevel> selectByLevelName(@Param("levelName")String levelName,@Param("start")Integer start,@Param("pageSize")Integer pageSize );

    Integer countByLevelName(@Param("levelName")String levelName);

    List<MemberLevel> findAll();


}