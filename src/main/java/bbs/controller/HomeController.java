package bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主要控制未登录情况下的数据与页面
 */
@Controller
public class HomeController {
    @RequestMapping({"/","index"})
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping("/resource")
    public String resource(){
        return "resource";
    }
}
