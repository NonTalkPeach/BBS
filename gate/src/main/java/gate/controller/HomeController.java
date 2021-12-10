package gate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 无登录状态下的页面跳转
 */
@Controller
public class HomeController {

    @GetMapping({"/","/index"})
    public String index(){
        return "index";
    }

    @GetMapping("/resource")
    public String resource(){
        return "resource";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }
}
