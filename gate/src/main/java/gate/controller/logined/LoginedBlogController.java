package gate.controller.logined;

import base.correspond.CorrespondBean;
import com.auth0.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * !!!!!!!!!!!!!!!
 * 注意，由于aop使用验证身份
 * 本类该方法首个参数必须为token
 */
@Controller
public class LoginedBlogController {
    @Autowired
    RestTemplate restTemplate;

    private static final String REST_URL_PREFIX_BLOG = "http://BLOG-SERVICE/api/blog";
    /**
     * 发布博客
     * @param userToken
     * @param title
     * @param content
     * @return
     */
    @PostMapping("/publishBlog")
    @ResponseBody
    public String publishBlog(String userToken, String title, String content) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("userCode", JWT.decode(userToken).getClaim("userInfo").asMap().get("userCode"));
        map.add("content",content);
        map.add("title",title);
        CorrespondBean correspondBean = restTemplate.postForObject(
                REST_URL_PREFIX_BLOG + "/publishBlog",
                map,
                CorrespondBean.class);
        return correspondBean.getMessage();
    }
}
