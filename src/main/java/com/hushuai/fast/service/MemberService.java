package com.hushuai.fast.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.hushuai.fast.dao.MemberMapper;
import com.hushuai.fast.dto.Member;
@Service
public class MemberService{

    @Resource
    private MemberMapper memberMapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return memberMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(Member record) {
        return memberMapper.insert(record);
    }

    
    public int insertSelective(Member record) {
        return memberMapper.insertSelective(record);
    }

    
    public Member selectByPrimaryKey(Integer id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(Member record) {
        return memberMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(Member record) {
        return memberMapper.updateByPrimaryKey(record);
    }

}
