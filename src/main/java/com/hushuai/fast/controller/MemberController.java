package com.hushuai.fast.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hushuai.fast.dto.Member;
import com.hushuai.fast.dto.MemberLevel;
import com.hushuai.fast.service.MemberLevelService;
import com.hushuai.fast.service.MemberService;
import com.hushuai.fast.vo.MemberVo;
import com.hushuai.fast.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: shuaihu2
 * @Date: 2019/7/17
 * @Interface: MemberController
 * @Description:
 */
@Controller
@RequestMapping("member")
public class MemberController {

    @Autowired
    private MemberLevelService memberLevelService;

    @Autowired
    private MemberService memberService;


    @GetMapping("/createMemberPage")
    public String createMemberPage(Model model) {
        List<MemberLevel> all = memberLevelService.findAll();
        model.addAttribute("memberLevelList", all);
        return "member/createmember.html";
    }

    @PostMapping("/createMember")
    @ResponseBody
    public String createMember(MemberVo memberVo, HttpServletRequest request) {
        if (ObjectUtils.isEmpty(memberVo)) {
            return JSON.toJSONString(new ResultVo(1, "传入参数有缺失，请重试！"));
        }
        boolean flag = memberService.createMember(memberVo);
        if (flag) {

            return JSON.toJSONString(new ResultVo(0, "创建用户成功！"));
        }
        return JSON.toJSONString(new ResultVo(1, "创建用户失败，请联系管理员！"));
    }

    @PostMapping("/getMemberList")
    @ResponseBody
    public String getMemberList(MemberVo memberVo) {
        JSONObject result = new JSONObject();
        List<MemberVo> memberList = memberService.getMemberList(memberVo);
        Integer total = memberService.countMemberListByParms(memberVo);
        result.put("total",total);
        result.put("rows",memberList);
        return result.toJSONString();
    }

    @PostMapping("/delMember")
    @ResponseBody
    public String delMember(MemberVo memberVo){
        if (memberVo == null || memberVo.getId() == null ){
            ResultVo resultVo = new ResultVo(1, "参数不能为空！");
            return JSON.toJSONString(resultVo);
        }
        int flag = memberService.deleteByPrimaryKey(memberVo.getId());
        if (flag == 1){
            return JSON.toJSONString(new ResultVo(0,"删除成功！"));
        }
        return JSON.toJSONString(new ResultVo(1,"删除失败，请联系管理员！"));
    }

    @PostMapping("/changeMember")
    @ResponseBody
    public String changerMember(MemberVo memberVo){
        if (memberVo == null || memberVo.getId() == null ){
            ResultVo resultVo = new ResultVo(1, "参数不能为空！");
            return JSON.toJSONString(resultVo);
        }
        Member member = new Member();
        BeanUtils.copyProperties(memberVo,member);
        int flag = memberService.updateByPrimaryKeySelective(member);
        if (flag == 1){
            return JSON.toJSONString(new ResultVo(0,"修改成功！"));
        }
        return JSON.toJSONString(new ResultVo(1,"修改失败，请联系管理员！"));
    }

    @PostMapping("/recharge")
    @ResponseBody
    public String recharge(MemberVo memberVo){
        if (memberVo == null || memberVo.getId() == null ){
            ResultVo resultVo = new ResultVo(1, "参数不能为空！");
            return JSON.toJSONString(resultVo);
        }
        int flag = memberService.recharge(memberVo);
        if (flag == 1){
            return JSON.toJSONString(new ResultVo(0,"充值成功成功！"));
        }
        return JSON.toJSONString(new ResultVo(1,"充值失败，请联系管理员！"));
    }
}
