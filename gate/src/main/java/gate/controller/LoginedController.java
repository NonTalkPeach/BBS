package gate.controller;

import base.correspond.CorrespondBean;
import com.auth0.jwt.JWT;
import gate.utils.ForwardUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static final String REST_URL_PREFIX_AUTH = "http://AUTH-SERVICE/api/auth";
    private static final String REST_URL_PREFIX_FILE = "http://FILE-SERVICE/api/file";

    @RequestMapping("/my/{token}")
    public String my(@PathVariable("token") String token, Model model) {
        model.addAttribute("token", token);
        return "my";
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
                               String avatarData,
                               Model model) {
        HttpEntity httpEntity = ForwardUtil.getHttpEntityForFile("avatarFile", file);
        MultiValueMap body = (MultiValueMap)httpEntity.getBody();
        body.add("userCode", JWT.decode(userToken).getClaim("userInfo").asMap().get("userCode"));
        CorrespondBean correspondBean = restTemplate.postForObject(REST_URL_PREFIX_FILE + "/uploadAvatar", httpEntity, CorrespondBean.class);
        if (correspondBean.getCode() == CorrespondBean.SUCCESS) {
            return "{\"result\":\"" +correspondBean.getData()+"\"}";
        }else return "上传失败";

    }
}
