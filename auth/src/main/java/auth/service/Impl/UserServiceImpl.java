package auth.service.Impl;

import auth.dao.UserMapper;
import auth.entity.User;
import auth.service.UserService;
import auth.utils.JWTTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User tryGetUser(String emailOrUsername) {
        return userMapper.getUserByEmailOrUsername(emailOrUsername);
    }

    /**
     * 登录服务
     * @param emailOrUsername 用户名
     * @param passwd 密码
     * @return 返回null 表示用户名或密码错误 返回token 表示用户名密码正确
     */
    @Override
    public String toLogin(String emailOrUsername, String passwd,User localUser){
        if(!localUser.getPasswd().equals(passwd)) return null;
        else return JWTTokenUtil.getToken(localUser.getUsername(),localUser.getUserCode());
    }

    @Override
    public int toRegister(User user) {
        userMapper.addUser(user);
        return 1;
    }

    @Override
    public int refreshCode(String emailOrUsername) {
        return 0;
    }
}
