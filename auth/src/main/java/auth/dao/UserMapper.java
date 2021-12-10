package auth.dao;

import auth.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    /**
     * email 和 username 都可定位到唯一用户 用于登录
     * @param emailOrUsername
     * @return
     */
    public User getUserByEmailOrUsername(String emailOrUsername);

    /**
     * 添加新用户
     * @param user
     * @return
     */
    public int addUser(User user);

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
     * @param emailOrUsername
     * 目的 emailOrUsername
     * @param newCode
     * 新注册码
     * @return
     * 返回值 1 表示激活成功 0 表示激活失败
     */
    int updateUserCode(String emailOrUsername,String newCode);
}
