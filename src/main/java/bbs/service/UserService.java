package bbs.service;

import bbs.entity.User;

public interface UserService {
    /**
     * 查询
     * @param email
     * 待查用户email或用户名字
     * @return
     * 返回 用户实体
     */
    User getUserByEmail(String email);
    User getUserByUsername(String username);

    /**
     * 添加用户
     * @param user
     * @return
     * 0表示失败，1表示成功
     */
    int addUser(User user);
}
