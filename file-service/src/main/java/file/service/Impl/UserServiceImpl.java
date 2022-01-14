package file.service.Impl;

import file.dao.UserMapper;
import file.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public int updateUserAvatar(String userCode, String avatarUrl) {
        userMapper.updateUserAvatarUrlByUserCode(userCode,avatarUrl);
        return 1;
    }
}
