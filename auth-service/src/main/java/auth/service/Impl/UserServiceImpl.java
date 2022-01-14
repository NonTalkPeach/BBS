package auth.service.Impl;

import auth.dao.UserMapper;
import auth.entity.User;
import auth.service.UserService;
import auth.utils.JWTTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User tryGetUser(String emailOrUsername) {
        return userMapper.selectUserByEmailOrUsername(emailOrUsername);
    }

    /**
     * 登录服务
     * @param emailOrUsername 用户名
     * @param passwd 密码
     * @return 返回null 表示用户名或密码错误 返回token 表示用户名密码正确
     */
    @Override
    public String toLogin(String emailOrUsername, String passwd){
        User localUser = userMapper.selectUserByEmailOrUsername(emailOrUsername);
        if (localUser == null || !localUser.getPasswd().equals(passwd)) return null;
        else {
            Map<String, Object> claimValues = new HashMap<>();
            claimValues.put("userCode",localUser.getUserCode());
            claimValues.put("username",localUser.getUsername());
            claimValues.put("email",localUser.getEmail());
            claimValues.put("level",localUser.getLevel());
            claimValues.put("avatarUrl",localUser.getAvatarUrl());
            return JWTTokenUtil.getToken(claimValues);
        }
    }

    @Override
    public int toRegister(User user) {
        userMapper.insertOneUser(user);
        return 1;
    }
}
