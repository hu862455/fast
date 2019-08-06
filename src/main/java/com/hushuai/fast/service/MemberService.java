package com.hushuai.fast.service;

import com.hushuai.fast.dao.MemberMapper;
import com.hushuai.fast.dto.Member;
import com.hushuai.fast.dto.SysUser;
import com.hushuai.fast.vo.MemberVo;
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
public class MemberService {

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
}



