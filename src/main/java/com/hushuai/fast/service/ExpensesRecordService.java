package com.hushuai.fast.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.hushuai.fast.dao.ExpensesRecordMapper;
import com.hushuai.fast.dto.ExpensesRecord;
@Service
public class ExpensesRecordService{

    @Resource
    private ExpensesRecordMapper expensesRecordMapper;

    
    public int deleteByPrimaryKey(Integer id) {
        return expensesRecordMapper.deleteByPrimaryKey(id);
    }

    
    public int insert(ExpensesRecord record) {
        return expensesRecordMapper.insert(record);
    }

    
    public int insertSelective(ExpensesRecord record) {
        return expensesRecordMapper.insertSelective(record);
    }

    
    public ExpensesRecord selectByPrimaryKey(Integer id) {
        return expensesRecordMapper.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(ExpensesRecord record) {
        return expensesRecordMapper.updateByPrimaryKeySelective(record);
    }

    
    public int updateByPrimaryKey(ExpensesRecord record) {
        return expensesRecordMapper.updateByPrimaryKey(record);
    }

}
