package com.hushuai.fast.dao;

import com.hushuai.fast.dto.ExpensesRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExpensesRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExpensesRecord record);

    int insertSelective(ExpensesRecord record);

    ExpensesRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExpensesRecord record);

    int updateByPrimaryKey(ExpensesRecord record);
}