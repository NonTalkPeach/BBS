package auth.controller;

import auth.entity.User;
import auth.service.UserService;
import base.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/toLogin")
    public ResultBean toLogin(String emailOrUsername,String passwd){
        User user = userService.tryGetUser(emailOrUsername);
        if(user == null)
            return new ResultBean(500,"系统无该用户或未激活，请注册或先激活",null);
        String token = userService.toLogin(emailOrUsername, passwd, user);
        if(token != null)
            return new ResultBean(200,"登录成功",token);
        else
            return new ResultBean(500,"用户名或密码错误",null);
    }

}
