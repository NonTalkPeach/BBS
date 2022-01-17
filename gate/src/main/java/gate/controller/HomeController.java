package gate.controller;

import base.correspond.CorrespondBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

/**
 * 无登录状态下的页面跳转
 */
@Controller
public class HomeController {
    @Value("${myconfig.file-server-url}")
    private String fileServerUrl;

    @Autowired
    RestTemplate restTemplate;
    private static final String REST_URL_PREFIX_FILE = "http://FILE-SERVICE/api/file";

    @GetMapping({"/","/index"})
    public String index(){
        return "index";
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
