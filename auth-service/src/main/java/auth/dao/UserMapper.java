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
    public User selectUserByEmailOrUsername(String emailOrUsername);

    /**
     * 添加新用户
     * @param user
     * @return
     */
    public int insertOneUser(User user);

}
