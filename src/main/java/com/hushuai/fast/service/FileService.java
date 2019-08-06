package com.hushuai.fast.service;

import com.hushuai.fast.dto.Member;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: shuaihu2
 * @Date: 2019/8/6
 * @Interface: FileService
 * @Description:
 */
@Service
@Transactional
public class FileService extends CommonService{

    @Autowired
    MemberLevelService memberLevelService;

    @Autowired
    MemberService memberService;

    public void importMemberList(String filePath){
        filePath = "D://test//"+"2019-08-06-55-51会员信息表.xls";

        // 清除member表
        memberService.truncateMemberTable();
        List<Member> memberList = new ArrayList<>();
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(filePath));
            HSSFSheet memberListTable = workbook.getSheet("Table");
            int lastRowNum = memberListTable.getLastRowNum();
            for (int i = 1; i < lastRowNum; i++) {
                HSSFRow row = memberListTable.getRow(i);
                // 创建会员实体
                Member memberVo = new Member();
                // 1会员id
                memberVo.setId(Integer.parseInt(row.getCell(0).getStringCellValue()));
                // 2会员名称
                memberVo.setName(row.getCell(1).getStringCellValue());
                // 3会员电话
                memberVo.setTelephone(row.getCell(2).getStringCellValue());
                // 4会员折扣
//                memberVo.setCount(row.getCell(3).getNumericCellValue());
                // 5会员地址
                memberVo.setAddress(row.getCell(4).getStringCellValue());
                // 6会员性别
                memberVo.setSex(sexName2Integer(row.getCell(5).getStringCellValue()));
                // 7账户余额
                memberVo.setAccount( new BigDecimal(row.getCell(6).getNumericCellValue()));
                // 8会员等级
//                memberVo.setLevelName(row.getCell(7).getStringCellValue());
                memberVo.setMemberLevelId(memberLevelService.memberLevelName2MemberLevelId(row.getCell(7).getStringCellValue()));
                // 9充值总额
                memberVo.setTotalAccount(new BigDecimal(row.getCell(8).getNumericCellValue()));
                memberList.add(memberVo);

                // 其他属性
                memberVo.setCreateTime(new Date());
                memberVo.setUpdateTime(new Date());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        memberService.insertList(memberList);
    }
}
