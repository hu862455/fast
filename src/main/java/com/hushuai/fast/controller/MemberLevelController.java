package com.hushuai.fast.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.hushuai.fast.dto.MemberLevel;
import com.hushuai.fast.service.MemberLevelService;
import com.hushuai.fast.vo.MemberLevelVo;
import com.hushuai.fast.vo.ResultVo;
import com.mysql.cj.xdevapi.JsonParser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: shuaihu2
 * @Date: 2019/7/22
 * @Interface: MemberLevelController
 * @Description:
 */
@Controller
@RequestMapping("/memberLevel")
public class MemberLevelController {

    @Autowired
    private MemberLevelService memberLevelService;

    @RequestMapping("/memberLevelManagerPage")
    public String memberLevelManagerPage(){
        return "/member/memberLevel";
    }

    @RequestMapping(value = "/memberLevelList",method = RequestMethod.GET)
    @ResponseBody
    public String memberLevelList(MemberLevelVo memberLevel){
        JSONObject result = new JSONObject();
        Integer total = memberLevelService.countByLevelName(memberLevel);
        List<MemberLevel> memberLevels = memberLevelService.selectByLevelName(memberLevel);
        result.put("rows",memberLevels);
        result.put("total",total);

        return result.toJSONString();
    }

    @PostMapping("/addMemberLevel")
    @ResponseBody
    public String addMemberLevel(MemberLevelVo memberLevel){

        if (memberLevel == null || memberLevel.getLevelName().isEmpty()
                || memberLevel.getCount() == null || memberLevel.getLimit() == null ){
            ResultVo resultVo = new ResultVo(1, "参数不能为空！");
            return JSON.toJSONString(resultVo);
        }

        MemberLevel level = new MemberLevel();
        BeanUtils.copyProperties(memberLevel,level);
        memberLevelService.insert(level);
        return JSON.toJSONString(new ResultVo(0,"添加成功！"));
    }

    @PostMapping("/updateMemberLevel")
    @ResponseBody
    public String updateMemberLevel(MemberLevelVo memberLevel){

        if (memberLevel == null || memberLevel.getId() == null ){
            ResultVo resultVo = new ResultVo(1, "参数不能为空！");
            return JSON.toJSONString(resultVo);
        }

        MemberLevel level = new MemberLevel();
        BeanUtils.copyProperties(memberLevel,level);
        memberLevelService.updateByPrimaryKeySelective(level);
        return JSON.toJSONString(new ResultVo(0,"修改成功！"));
    }

    @PostMapping("/delMemberLevel")
    @ResponseBody
    public String delMemberLevel(MemberLevelVo memberLevel){
        if (memberLevel == null || memberLevel.getId() == null ){
            ResultVo resultVo = new ResultVo(1, "参数不能为空！");
            return JSON.toJSONString(resultVo);
        }
        int flag = memberLevelService.deleteByPrimaryKey(memberLevel.getId());
        if (flag == 1){
            return JSON.toJSONString(new ResultVo(0,"删除成功！"));
        }
        return JSON.toJSONString(new ResultVo(1,"删除失败，请联系管理员！"));
    }

    @PostMapping("/allMemberLevel")
    @ResponseBody
    public String allMemberLevel(){
        return JSON.toJSONString(memberLevelService.findAll());
    }
}
