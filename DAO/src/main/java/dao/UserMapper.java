package auth.dao;


import auth.entity.User;

import java.util.List;

public interface UserMapper {
    /**
     * 查新用户
     * @return
     * 返回用户或用户列表
     */
    List<User> getAllUsers();
    List<User> getUsersByLevel(int level);
    User getUserByEmail(String email);
    User getUserByUsername(String username);

    /**
     * 插入新用户
     */
    int addUser(User user);

    /**
     *
     * @param userCode
     * 目标激活码
     * @return
     * 返回值 1 表示激活成功 0 表示激活失败
     */
    int active(String userCode);

    /**
     * 用于重发注册码，更新数据库内注册码
     * @param email
     * 目的email
     * @param newCode
     * 新注册码
     * @return
     * 返回值 1 表示激活成功 0 表示激活失败
     */
    int updateUserCode(String email,String newCode);
}
