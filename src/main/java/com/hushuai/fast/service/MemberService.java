package com.hushuai.fast.service;

import com.hushuai.fast.dao.MemberMapper;
import com.hushuai.fast.dto.Member;
import com.hushuai.fast.dto.SysUser;
import com.hushuai.fast.vo.MemberVo;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Class_name: MemberService
 * @Exception:
 * @Describe: TODO
 * @Author: shuaihu2
 * @Creat_date: 2019/7/27 11:54
 **/
@Service
public class MemberService extends CommonService{

    @Resource
    private MemberMapper memberMapper;

    @Autowired
    private SysUserService sysUserService;

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

    public boolean createMember(MemberVo memberVo) {
        Member member = new Member();
        BeanUtils.copyProperties(memberVo, member);
        member.setCreateTime(new Date());
        SysUser curUser = sysUserService.getCurUser();
        member.setCreateUser(curUser.getId());
        member.setTotalAccount(memberVo.getAccount());
        int insert = this.insert(member);
        if (insert == 1) {
            return true;
        } else {
            return false;
        }
    }

    public List<MemberVo> getMemberList(MemberVo memberVo) {

        if (memberVo.getPageSize() == null || memberVo.getPageNumber() == null) {
            return memberMapper.selectMemberListByParms(memberVo, null, null);
        } else {
            Integer pageSize = memberVo.getPageSize();
            Integer start = (memberVo.getPageNumber() - 1) * pageSize;
            return memberMapper.selectMemberListByParms(memberVo, start, pageSize);
        }
    }

    public Integer countMemberListByParms(MemberVo memberVo) {
        return memberMapper.countMemberListByParms(memberVo);
    }

    public int recharge(MemberVo memberVo) {
        // 根据id查询member实体
        Member member = memberMapper.selectByPrimaryKey(memberVo.getId());
        member.setAccount(member.getAccount().add(memberVo.getRecharge()));
        member.setTotalAccount(member.getTotalAccount().add(memberVo.getRecharge()));
        return memberMapper.updateByPrimaryKeySelective(member);
    }

	public int insertList(List<Member> list){
		 return memberMapper.insertList(list);
	}

	public void truncateMemberTable(){
        memberMapper.truncateMemberTable();
    }

    public HSSFWorkbook exportExcel(MemberVo memberVo){

        String[] tableHeaders = {"id", "会员名字", "电话号码","会员折扣","地址","性别","账户余额","会员等级","充值总额"};

        List<MemberVo> memberList = getMemberList(memberVo);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Table");
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        Font font = workbook.createFont();
        font.setBold(true);
        cellStyle.setFont(font);
        // 创建表头
        HSSFRow header = sheet.createRow(0);
        for (int i = 0; i < tableHeaders.length; i++) {
            HSSFCell cell = header.createCell(i);
            cell.setCellValue(tableHeaders[i]);
            cell.setCellStyle(cellStyle);
        }

        // 填充内容
        for (int i = 0; i < memberList.size(); i++) {
            HSSFRow row = sheet.createRow(1 + i);
            row.createCell(0).setCellValue(memberList.get(i).getId().toString());
            row.createCell(1).setCellValue(memberList.get(i).getName());
            row.createCell(2).setCellValue(memberList.get(i).getTelephone());
            row.createCell(3).setCellValue(memberList.get(i).getCount());
            row.createCell(4).setCellValue(memberList.get(i).getAddress());
            row.createCell(5).setCellValue(sexInteger2String(memberList.get(i).getSex()));
            row.createCell(6).setCellValue(memberList.get(i).getAccount().doubleValue());
            row.createCell(7).setCellValue(memberList.get(i).getLevelName());
            row.createCell(8).setCellValue(memberList.get(i).getTotalAccount().doubleValue());
        }
        return workbook;

    }
}



