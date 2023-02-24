package file.controller;

import base.correspond.CorrespondBean;
import com.auth0.jwt.JWT;
import file.service.ResourceService;
import file.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
/**
 * 该类接口不对外开放 使用AOP拦截
 * 本类方法第一个参数必为 userToken
 */
@Controller
@RequestMapping("/api/file")
public class UploadController {

    @Value("${myconfig.file-location}")
    private String fileLocation;

    @Value("${myconfig.public-file-location}")
    private String publicFileLocation;

    @Value("${myconfig.web-url}")
    private String webUrl;

    @Autowired
    UserService userService;

    @Autowired
    private ResourceService resourceService;

    /**
     * 上传头像
     * @param userToken
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadAvatar")
    @ResponseBody
    public CorrespondBean uploadAvatar(String userToken, @RequestParam("avatarFile") CommonsMultipartFile file) throws IOException {
        String userCode = (String) JWT.decode(userToken).getClaim("userInfo").asMap().get("userCode");
        String path ="/avatarImg/" + userCode + ".jpg";
        userService.updateUserAvatar(userCode,path);
        file.transferTo(new File( publicFileLocation + path));
//        return "redirect:" + webUrl + "/uploadAvatar/" + userToken + "/" + "200/" + path;
        return CorrespondBean.getSuccessBean("头像上传成功！",path);
    }

    /**
     * 上传博客图片
     * @param userToken
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadBlogImg")
    @ResponseBody
    public CorrespondBean uploadBlogImg(String userToken, @RequestParam("blogImg") CommonsMultipartFile file) throws IOException {
        String userCode = (String) JWT.decode(userToken).getClaim("userInfo").asMap().get("userCode");
        String path ="/blogImg/" + userCode + "_" + System.currentTimeMillis()+ ".jpg";
        file.transferTo(new File( publicFileLocation + path));
//        return "redirect:" + webUrl + "/uploadBlogImg/" + userToken + "/" + "200/" + path;
        return CorrespondBean.getSuccessBean("博客图片上传成功！",path);
    }

    /**
     * 上传文件
     * @param file
     * @param userToken
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadPublicFile")
    public String uploadPublicFile(String userToken, @RequestParam("publicFile") CommonsMultipartFile file) throws IOException {
        String userCode = (String) JWT.decode(userToken).getClaim("userInfo").asMap().get("userCode");
        String timestamp = String.valueOf(System.currentTimeMillis());
        resourceService.insertResource(file.getOriginalFilename(),userCode,timestamp);
        String path = fileLocation + "/resources/" + userCode + "_" + timestamp + "_" + file.getOriginalFilename();
        file.transferTo(new File(path));
        return "redirect:" + webUrl + "/uploadPublicFile/" + userToken + "/" + "success！";
        //return CorrespondBean.getSuccessBean("文件上传成功！");
    }
}
