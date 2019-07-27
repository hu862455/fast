package com.hushuai.fast.service;
import java.util.List;

import com.hushuai.fast.vo.MemberLevelVo;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.hushuai.fast.dao.MemberLevelMapper;
import com.hushuai.fast.dto.MemberLevel;
/**
 * @Class_name: MemberLevelService
 * @Exception:
 * @Describe: TODO
 * @Author: shuaihu2
 * @Creat_date: 2019/7/27 10:12
 **/
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

	public List<MemberLevel> selectByLevelName(MemberLevelVo memberLevel){

        if(memberLevel.getPageSize() == null || memberLevel.getPageNumber() == null){
            return memberLevelMapper.selectByLevelName(memberLevel.getLevelName(),null,null);
        }else{
            String levelName = memberLevel.getLevelName();
            Integer pageSize = memberLevel.getPageSize();
            Integer start = (memberLevel.getPageNumber()-1)*pageSize;
            return memberLevelMapper.selectByLevelName(levelName,start,pageSize);
        }
	}

	public Integer countByLevelName(MemberLevelVo memberLevel){
		 return memberLevelMapper.countByLevelName(memberLevel.getLevelName());
	}

	public List<MemberLevel> findAll(){
		 return memberLevelMapper.findAll();
	}










}
