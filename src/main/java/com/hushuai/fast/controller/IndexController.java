package com.hushuai.fast.controller;

import com.hushuai.fast.constants.ResultConstant;
import com.hushuai.fast.vo.ResultVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: shuaihu2
 * @Date: 2019/7/17
 * @Interface: IndexController
 * @Description:
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping(value = "/404Page")
    public String errorPage(){
        return "404";
    }

    @RequestMapping(value = "loginPage")
    public String loginPage(String error ,Model model){
        model.addAttribute("");
        return "login";
    }

}
