package file.controller;

import base.correspond.CorrespondBean;
import file.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

@Controller
@ResponseBody
public class DownloadController {
    @Value("${myconfig.file-location}")
    private String fileLocation;

    @Autowired
    private ResourceService resourceService;

    /**
     * 由于出于性能考虑与下载量数据不需要严格的准确性
     * 可能出现下载文件与下载量加一的文件不一致
     * @param location
     * @param fid
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/get/resources/{location}")
    public CorrespondBean downloadFile (
            @PathVariable("location") String location,
            int fid,
            HttpServletResponse response) throws IOException {
        //下载量 + 1
        resourceService.updateDownloadSumPlusOne(fid);

        String path = fileLocation + "/resources/" + location;
        File file = new File(path);
        if (!file.exists()) {
            return CorrespondBean.getFailBean("不存在该文件");
        }
        FileInputStream inputStream = new FileInputStream(file);
        response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(location.split("_")[2],"utf-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] buffer = new byte[1024*1024];
        int len = 0;
        while ((len=inputStream.read(buffer))>0){
            outputStream.write(buffer,0,len);
        }
        outputStream.close();
        inputStream.close();
        return CorrespondBean.getSuccessBean("传输成功");
    }
}
