package gate.controller;

import base.correspond.CorrespondBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

/**
 * 无登录状态下的页面跳转
 */
@Controller
public class HomeController {
    private static final String REST_URL_PREFIX_FILE = "http://FILE-SERVICE/api/file";
    private static final String REST_URL_PREFIX_Blog = "http://BLOG-SERVICE/api/blog";

    @Value("${myconfig.file-server-url}")
    private String fileServerUrl;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping({"/","/index"})
    public String index(Model model){
        CorrespondBean correspondBean = restTemplate.getForObject(
                REST_URL_PREFIX_Blog + "/getAllBlogs",
                CorrespondBean.class
        );
        model.addAttribute("token","");
        model.addAttribute("FILE_SERVER_URL",fileServerUrl);
        model.addAttribute("blogs",correspondBean.getData());
        return "index";
    }

    @GetMapping("/blog/{blogId}")
    public String getOneBlog(@PathVariable("blogId") int blogId, Model model){
        CorrespondBean correspondBean = restTemplate.getForObject(REST_URL_PREFIX_Blog + "/getOneBlog/" + blogId, CorrespondBean.class);
        model.addAttribute("FILE_SERVER_URL",fileServerUrl);
        model.addAttribute("blog",correspondBean.getData());
        return "blog";
    }

    @GetMapping("/resource")
    public String resource(Model model){
        CorrespondBean correspondBean = restTemplate.getForObject(REST_URL_PREFIX_FILE + "/getAllPublicResources", CorrespondBean.class);
        model.addAttribute("publicResources",correspondBean.getData());
        model.addAttribute("FILE_SERVER_URL",fileServerUrl);
        return "resource";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }
}
