package gate.controller;

import base.correspond.CorrespondBean;
import base.correspond.ForwardUtil;
import gate.config.WangEditorResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * !!!!!!!!!!!!!!!
 * 注意，由于aop使用验证身份
 * 本类该方法首个参数必须为token
 */
@Controller
public class LoginedController {
    @Autowired
    RestTemplate restTemplate;
    @Value("${myconfig.file-server-url}")
    private String fileServerUrl;

    private static final String REST_URL_PREFIX_FILE = "http://FILE-SERVICE/api/file";

    @RequestMapping("/my/{token}")
    public String my(@PathVariable("token") String token, Model model) {
        model.addAttribute("token", token);
        return "my";
    }

    @RequestMapping("/editor/{token}")
    public String editor(@PathVariable("token") String token, Model model) {
        model.addAttribute("token", token);
        return "editor";
    }

    /**
     * 上传头像
     * @param file 头像文件
     * @param avatarSrc 不知道什么东西，是null的
     * @param avatarData 头像文件相关data
     * @param userToken
     * @return
     */
    @PostMapping("/uploadAvatar")
    @ResponseBody
    public String uploadAvatar(
                               String userToken,
                               @RequestParam("avatarFile") CommonsMultipartFile file,
                               String avatarSrc,
                               String avatarData
                               ) {
        HttpEntity httpEntity = ForwardUtil.getHttpEntityForFile("avatarFile", file);
        MultiValueMap body = (MultiValueMap)httpEntity.getBody();
        body.add("userToken", userToken);
        CorrespondBean correspondBean = restTemplate.postForObject(REST_URL_PREFIX_FILE + "/uploadAvatar", httpEntity, CorrespondBean.class);
        if (correspondBean.getCode() == CorrespondBean.SUCCESS) {
            return "{\"result\":\"" + fileServerUrl + correspondBean.getData()+"\"}";
        }else return "上传失败";
    }

    /**
     * 上传blog 图片
     * @param userToken
     * @param file
     * @return
     */
    @PostMapping("/uploadBlogImg")
    @ResponseBody
    public WangEditorResponseBean uploadBlogImg(
            String userToken,
            @RequestParam("blogImg") CommonsMultipartFile file
            ) {
        HttpEntity httpEntity = ForwardUtil.getHttpEntityForFile("blogImg", file);
        MultiValueMap body = (MultiValueMap)httpEntity.getBody();
        body.add("userToken", userToken);
        CorrespondBean correspondBean = restTemplate.postForObject(REST_URL_PREFIX_FILE + "/uploadBlogImg", httpEntity, CorrespondBean.class);
        if (correspondBean.getCode() == CorrespondBean.SUCCESS) {
            return new WangEditorResponseBean("0",fileServerUrl+correspondBean.getData());
        } else return new WangEditorResponseBean("err0r","上传失败");
    }

    /**
     * 上传公开文件
     * @param userToken
     * @param file
     * @param model
     * @return
     */
    @PostMapping("/uploadPublicFile")
    public String uploadPublicFile(String userToken,
                                   @RequestParam("publicFile") CommonsMultipartFile file,
                                   Model model) {
        HttpEntity httpEntity = ForwardUtil.getHttpEntityForFile("publicFile", file);
        MultiValueMap body = (MultiValueMap)httpEntity.getBody();
        body.add("userToken", userToken);
        CorrespondBean correspondBean = restTemplate.postForObject(REST_URL_PREFIX_FILE + "/uploadPublicFile", httpEntity, CorrespondBean.class);
        model.addAttribute("msg",correspondBean.getMessage());
        return "message";
    }
}
