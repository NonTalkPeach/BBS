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
        return resourceMapper.insertResource(new Resource(fileName,ownerCode,timestamp));
    }

    @Override
    public int updateDownloadSumPlusOne(int id) {
        return resourceMapper.updateDownloadSumPlusOne(id);
    }

    @Override
    public List<Resource> selectAllResource() {
        return resourceMapper.selectAllResources();
    }
}
