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
public class RegisterController {
    @Autowired
    private UserService userService;

    @PostMapping("/toRegister")
    public ResultBean toRegister(User user){
        return null;
    }

}
