package auth.controller;

import auth.service.UserService;
import auth.utils.JWTTokenUtil;
import base.correspond.CorrespondBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/api/auth")
public class LoginController {
    @Autowired
    private UserService userService;

    /**
     * 完成用户登录及token产生
     * @param emailOrUsername
     * @param passwd
     * @return
     */
    @PostMapping("/toLogin")
    public CorrespondBean toLogin(String emailOrUsername, String passwd){
        String token = userService.toLogin(emailOrUsername, passwd);
        if(token != null)
            return CorrespondBean.getSuccessBean("登录成功",token);
        else
            return CorrespondBean.getFailBean("用户名或密码错误");
    }

    /**
     * 认证是否登录，注意高并发
     * @param token
     * @return
     */
    @PostMapping("/toVerify")
    public CorrespondBean toVerify (String token) {
        if (token == null) return CorrespondBean.getFailBean("无效码！");
        boolean flag = JWTTokenUtil.verifyToken(token);
        if (flag) {
            return CorrespondBean.getSuccessBean("Pass!");
        }else {
            return CorrespondBean.getFailBean("无效登录或登录过期");
        }
    }
}
