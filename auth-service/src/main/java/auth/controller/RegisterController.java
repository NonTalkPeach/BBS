package auth.controller;

import auth.entity.User;
import auth.redis.RedisOpsForString;
import auth.service.UserService;
import auth.utils.MailUtil;
import auth.utils.PasswordUtil;
import base.correspond.CorrespondBean;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/api/auth")
public class RegisterController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisOpsForString redisOpsForString;

    @Autowired
    PasswordUtil passwordUtil;

    @Autowired
    private MailUtil mailUtil;

    /**
     * 注册，录入用户信息
     * @param username
     * @param email
     * @param passwd
     * @return
     */
    @PostMapping("/toRegister")
    public CorrespondBean toRegister(String username, String email, String passwd){
        if (redisOpsForString.getString(username) != null || userService.tryGetUser(username) != null)
            return CorrespondBean.getFailBean("错误！该用户名已被注册");
        if (redisOpsForString.getString(email) != null || userService.tryGetUser(email) != null)
            return CorrespondBean.getFailBean("错误！该邮箱已被注册");
        User user = new User(username, email, passwordUtil.encode(passwd));
        redisOpsForString.setString(username,"wyt",300);
        redisOpsForString.setString(email,"wyt",300);
        redisOpsForString.setString(user.getUserCode(), JSON.toJSONString(user),300);
        mailUtil.sendCode(email,user.getUserCode());
        return CorrespondBean.getSuccessBean(
                "录入成功，激活链接已发到您的邮箱，请在5分钟之内登录邮箱点击链接激活账户.\n" +
                "本站在5分钟之内为您保留您申请的邮箱和用户名,5分钟之后若未激活账号需重新申请注册."
        );
    }

    /**
     * 激活账户
     * @param userCode
     * @return
     */
    @RequestMapping("/toActive/{userCode}")
    public CorrespondBean toActive(@PathVariable("userCode") String userCode){
        String userJson = redisOpsForString.getString(userCode);
        User user = JSON.parseObject(userJson, User.class);
        if (userJson == null || userService.tryGetUser(user.getEmail()) != null || userService.tryGetUser(user.getUsername()) != null)
            return CorrespondBean.getFailBean("无效码!");
        else {
            userService.toRegister(user);
        }
        return CorrespondBean.getSuccessBean("激活成功!");
    }
}
