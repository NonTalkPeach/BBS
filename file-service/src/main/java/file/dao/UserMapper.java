package file.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    public int updateUserAvatarUrlByUserCode(@Param("userCode") String userCode, @Param("avatarUrl") String avatarUrl);
}
