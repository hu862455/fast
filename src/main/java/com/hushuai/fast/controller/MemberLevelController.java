package com.hushuai.fast.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hushuai.fast.dto.MemberLevel;
import com.hushuai.fast.service.MemberLevelService;
import com.hushuai.fast.vo.MemberLevelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
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
    private MemberLevelService MemberLevelService;

    @RequestMapping("/memberLevelManagerPage")
    public String memberLevelManagerPage(){
        return "/member/memberLevel";
    }

    @RequestMapping(value = "/memberLevelList",method = RequestMethod.GET)
    @ResponseBody
    public String memberLevelList(MemberLevelVo memberLevel){
        JSONObject result = new JSONObject();
        Integer total = MemberLevelService.countByLevelName(memberLevel);
        List<MemberLevel> memberLevels = MemberLevelService.selectByLevelName(memberLevel);
        result.put("rows",memberLevels);
        result.put("total",total);

        return result.toJSONString();
    }

}
