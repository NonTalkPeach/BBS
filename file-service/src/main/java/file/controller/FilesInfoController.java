package file.controller;

import base.correspond.CorrespondBean;
import file.entity.Resource;
import file.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("/api/file")
@ResponseBody
public class FilesInfoController {
    @Autowired
    private ResourceService resourceService;
    /**
     * 获取文件信息
     * @return
     */
    @GetMapping("/getAllPublicResources")
    public CorrespondBean getAllPublicResources() {
        List<Resource> resources = resourceService.selectAllResource();
        return CorrespondBean.getSuccessBean("获取成功", resources);
    }
}
