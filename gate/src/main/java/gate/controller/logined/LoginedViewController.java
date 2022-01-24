package gate.controller.logined;

import base.correspond.CorrespondBean;
import base.correspond.ForwardUtil;
import com.auth0.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;


/**
 * !!!!!!!!!!!!!!!
 * 注意，由于aop使用验证身份
 * 本类该方法首个参数必须为token
 */
@Controller
public class LoginedViewController {
    @Autowired
    RestTemplate restTemplate;
    @Value("${myconfig.file-server-url}")
    private String fileServerUrl;

    private static final String REST_URL_PREFIX_Blog = "http://BLOG-SERVICE/api/blog";


    @GetMapping("/index/{token}")
    public String index(@PathVariable("token") String userToken, Model model){
        CorrespondBean correspondBean = restTemplate.postForObject(
                REST_URL_PREFIX_Blog + "/getAllBlogsUniquely",
                ForwardUtil.getKeyValueMapForOneParam("userCode", JWT.decode(userToken).getClaim("userInfo").asMap().get("userCode")),
                CorrespondBean.class
        );
        model.addAttribute("token",userToken);
        model.addAttribute("FILE_SERVER_URL",fileServerUrl);
        model.addAttribute("blogs",correspondBean.getData());
        return "index";
    }

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
}
