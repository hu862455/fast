package com.hushuai.fast.controller;

import com.hushuai.fast.dto.MemberLevel;
import com.hushuai.fast.service.MemberLevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private MemberLevelService memberLevelService;

    @RequestMapping(value = "/404Page")
    public String error404Page(){
        return "error/404";
    }

    @RequestMapping(value = "/403Page")
    public String error403Page(){
        return "error/403";
    }

    @RequestMapping(value = "/500Page")
    public String error500Page(){
        return "error/500";
    }

    @RequestMapping(value = "loginPage")
    public String loginPage(String error ,Model model){
        model.addAttribute("");
        return "login";
    }

    @RequestMapping("/home")
    public String showHome() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info("当前登陆用户：" + name);
        return "home.html";
    }

    @RequestMapping("/createMemberPage")
    public String createMemberPage(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("curName",name);
        List<MemberLevel> levelServiceAll = memberLevelService.findAll();
        model.addAttribute("levelServiceAll",levelServiceAll);
        return "/member/createMemberPage.html";
    }

    @RequestMapping("/memberListPage")
    public String memberListPage(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("curName",name);
        List<MemberLevel> levelServiceAll = memberLevelService.findAll();
        model.addAttribute("levelServiceAll",levelServiceAll);
        return "/member/memberListPage.html";
    }

    @RequestMapping("/memberLevelPage")
    public String memberLevelPage(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("curName",name);
        List<MemberLevel> levelServiceAll = memberLevelService.findAll();
        model.addAttribute("levelServiceAll",levelServiceAll);
        return "/member/memberLevelPage.html";
    }

    @RequestMapping("/memberImportPage")
    public String memberImportPage(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("curName",name);
        return "/member/memberImportPage.html";
    }

    @RequestMapping("/index")
    public String showIndex() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info("当前登陆用户：" + name);
        return "index.html";
    }

    @RequestMapping("/admin")
    @ResponseBody
    @PreAuthorize("hasPermission('/user','ROLE_USER')")
    public String printAdmin() {
        return "如果你看见这句话，说明你有ROLE_ADMIN角色";
    }

    @RequestMapping("/user")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    public String printUser() {
        return "如果你看见这句话，说明你有ROLE_USER角色";
    }


}
