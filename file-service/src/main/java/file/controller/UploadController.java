package file.controller;

import base.correspond.CorrespondBean;
import base.correspond.ForwardUtil;
import com.auth0.jwt.JWT;
import file.service.ResourceService;
import file.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
/**
 * 该类接口不对外开放 使用AOP拦截
 * 本类方法第一个参数必为 userToken
 */
@Controller
public class UploadController {

    @Value("${myconfig.file-location}")
    private String fileLocation;

    @Value("${myconfig.public-file-location}")
    private String publicFileLocation;

    @Value("${myconfig.web-url}")
    private String webUrl;

    private static final String REST_URL_PREFIX_AUTH = "http://AUTH-SERVICE/api/auth";

    @Autowired
    RestTemplate restTemplate;

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
    @PostMapping("/api/file/uploadAvatar")
    @ResponseBody
    public CorrespondBean uploadAvatar(String userToken, @RequestParam("avatarFile") CommonsMultipartFile file) throws IOException {
        String userCode = (String) JWT.decode(userToken).getClaim("userInfo").asMap().get("userCode");
        String path ="/avatarImg/" + userCode + ".jpg";
        userService.updateUserAvatar(userCode,path);
        file.transferTo(new File( publicFileLocation + path));
        return CorrespondBean.getSuccessBean("头像上传成功！",path);
    }

    /**
     * 上传博客图片
     * @param userToken
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/api/file/uploadBlogImg")
    @ResponseBody
    public CorrespondBean uploadBlogImg(String userToken, @RequestParam("blogImg") CommonsMultipartFile file) throws IOException {
        String userCode = (String) JWT.decode(userToken).getClaim("userInfo").asMap().get("userCode");
        String path ="/blogImg/" + userCode + "_" + System.currentTimeMillis()+ ".jpg";
        file.transferTo(new File( publicFileLocation + path));
        return CorrespondBean.getSuccessBean("博客图片上传成功！",path);
    }

    /**
     * 上传文件
     * @param file
     * @param userToken
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadFile/uploadPublicFile")
    public String uploadPublicFile(
            @RequestParam("userToken")String userToken,
            @RequestParam("publicFile") CommonsMultipartFile file
    ) throws IOException {
        CorrespondBean correspondBean = restTemplate.postForObject(REST_URL_PREFIX_AUTH + "/toVerify", ForwardUtil.getKeyValueMapForOneParam("token", userToken), CorrespondBean.class);
        if (correspondBean.getCode() == CorrespondBean.FAIL) {
            return "redirect:" + webUrl + "/uploadPublicFile/errorToken/" + "IllegalToken!";
        }
        String userCode = (String) JWT.decode(userToken).getClaim("userInfo").asMap().get("userCode");
        String timestamp = String.valueOf(System.currentTimeMillis());
        resourceService.insertResource(file.getOriginalFilename(),userCode,timestamp);
        String path = fileLocation + "/resources/" + userCode + "_" + timestamp + "_" + file.getOriginalFilename();
        file.transferTo(new File(path));
        return "redirect:" + webUrl + "/uploadPublicFile/" + userToken + "/" + "success!";
    }
}
