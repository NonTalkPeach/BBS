package file.dao;

import file.entity.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ResourceMapper {
    public int insertResource(Resource resource);
    public List<Resource> selectAllResources();
}
