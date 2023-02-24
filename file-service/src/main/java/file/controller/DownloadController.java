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
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
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
    public void downloadFile (
            @PathVariable("location") String location,
            int fid,
            HttpServletResponse response) throws IOException {
        //下载量 + 1
        resourceService.updateDownloadSumPlusOne(fid);

        String path = fileLocation + "/resources/" + location;
        File file = new File(path);
        if (!file.exists()) {
            throw new RuntimeException("文件不存在");
        }


        String contentType = Files.probeContentType(Paths.get(file.getAbsolutePath()));

        response.setHeader("Content-Type", contentType);
        response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(location.split("_")[2],"utf-8"));
        response.setContentLength((int) file.length());

        FileInputStream inputStream = new FileInputStream(file);
        ServletOutputStream outputStream = response.getOutputStream();

        long start = System.currentTimeMillis();

//        byte[] buffer = new byte[1024*1024];
//        int len = 0;
//        while ((len=inputStream.read(buffer))>0){
//            outputStream.write(buffer,0,len);
//        }
//        outputStream.close();
//        inputStream.close();
        //获取输出流通道
        WritableByteChannel writableByteChannel = Channels.newChannel(outputStream);
        FileChannel fileChannel = inputStream.getChannel();
        //采用零拷贝的方式实现文件的下载
        fileChannel.transferTo(0,fileChannel.size(),writableByteChannel);
        //关闭对应的资源
        fileChannel.close();
        outputStream.flush();
        writableByteChannel.close();

        System.out.println((System.currentTimeMillis() - start)/1000);
    }
}
