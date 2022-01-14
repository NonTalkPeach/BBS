package auth.service;

import auth.entity.User;

public interface UserService {
    /**
     * 尝试获取某一用户
     * @param emailOrUsername 用户名或邮箱
     * @return
     */
    public User tryGetUser(String emailOrUsername);

    /**
     * 登录api
     * @param emailOrUsername 用户名
     * @param passwd 密码
     * @return 成功返回token 否则返回 null
     */
    public String toLogin(String emailOrUsername, String passwd);

    /**
     * 注册api
     * @param user 用户信息
     * @return 成功返回 1 否则返回 0
     */
    public int toRegister(User user);

}
