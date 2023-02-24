package gate.controller.logined;

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
public class LoginedUploadViewController {

    @Autowired
    RestTemplate restTemplate;
    @Value("${myconfig.file-server-url}")
    private String fileServerUrl;

    private static final String REST_URL_PREFIX_FILE = "http://FILE-SERVICE/api/file";

    /**
     * 上传头像
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
        CorrespondBean correspondBean = restTemplate.postForObject(
                REST_URL_PREFIX_FILE + "/uploadAvatar",
                httpEntity,
                CorrespondBean.class);
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
        CorrespondBean correspondBean = restTemplate.postForObject(
                REST_URL_PREFIX_FILE + "/uploadBlogImg",
                httpEntity,
                CorrespondBean.class);
        if (correspondBean.getCode() == CorrespondBean.SUCCESS) {
            return new WangEditorResponseBean("0",fileServerUrl+correspondBean.getData());
        } else return new WangEditorResponseBean("err0r","上传失败");
    }

    /**
     * 上传公开文件
     */
    @RequestMapping("/uploadPublicFile/{userToken}/{msg}")
    public String uploadPublicFile(@PathVariable("userToken") String userToken,
                                   @PathVariable("msg") String msg,
                                   Model model) {
//        HttpEntity httpEntity = ForwardUtil.getHttpEntityForFile("publicFile", file);
//        MultiValueMap body = (MultiValueMap)httpEntity.getBody();
//        body.add("userToken", userToken);
//        CorrespondBean correspondBean = restTemplate.postForObject(
//                REST_URL_PREFIX_FILE + "/uploadPublicFile",
//                httpEntity,
//                CorrespondBean.class);

        model.addAttribute("msg",msg);
        return "message";
    }
}
