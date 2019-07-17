package com.hushuai.fast.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping(value = "login")
    public String loginPage(){
        return "login";
    }
}
