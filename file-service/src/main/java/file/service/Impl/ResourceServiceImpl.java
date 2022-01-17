package file.service.Impl;

import file.dao.ResourceMapper;
import file.entity.Resource;
import file.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    ResourceMapper resourceMapper;
    @Override
    public int insertResource(String fileName, String ownerCode, String timestamp) {
        resourceMapper.insertResource(new Resource(fileName,ownerCode,timestamp));
        return 1;
    }

    @Override
    public List<Resource> selectAllResource() {
        return resourceMapper.selectAllResources();
    }
}
