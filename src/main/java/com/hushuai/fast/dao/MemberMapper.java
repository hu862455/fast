package com.hushuai.fast.dao;

import com.hushuai.fast.dto.Member;
import com.hushuai.fast.vo.MemberVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);

    List<MemberVo> selectMemberListByParms(@Param("memberVo")MemberVo memberVo, @Param("start")Integer start, @Param("pageSize")Integer pageSize );

    Integer countMemberListByParms(@Param("memberVo")MemberVo memberVo);
}