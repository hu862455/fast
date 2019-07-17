package com.hushuai.fast.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.hushuai.fast.dao.MemberLevelMapper;
import com.hushuai.fast.dto.MemberLevel;
@Service
public class MemberLevelService{

    @Resource
    private MemberLevelMapper memberLevelMapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return memberLevelMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(MemberLevel record) {
        return memberLevelMapper.insert(record);
    }

    
    public int insertSelective(MemberLevel record) {
        return memberLevelMapper.insertSelective(record);
    }

    
    public MemberLevel selectByPrimaryKey(Integer id) {
        return memberLevelMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(MemberLevel record) {
        return memberLevelMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(MemberLevel record) {
        return memberLevelMapper.updateByPrimaryKey(record);
    }

}
