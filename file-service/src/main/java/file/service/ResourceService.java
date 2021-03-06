package file.service;

import file.entity.Resource;

import java.util.List;

public interface ResourceService {
    public int insertResource(String fileName, String ownerCode, String timestamp);
    public int updateDownloadSumPlusOne(int id);
    public List<Resource> selectAllResource();
}
