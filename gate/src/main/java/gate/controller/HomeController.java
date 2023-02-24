package gate.controller;

import base.correspond.CorrespondBean;
import gate.utils.ImgCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

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

    /**
     * @param model 用于AOP
     * @return
     */
    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

    @GetMapping("/checkCode")
    public void checkCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置编码格式
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 设置不缓存图片
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", 0);

        // 指定生成的响应图片,一定不能缺少这句话,否则错误.
        response.setContentType("image/jpeg");
        /*
         * @param
         * 传入生成图像长度和宽度，还有本次会话的请求HttpServletRequest对象request
         * @return
         * 返回一个BufferedImage对象
         * */
        BufferedImage image = ImgCodeUtil.getPicture(120, 35, request);
        // 将图像输出到response输出流中。
        //response.getOutputStream()获取response输出流
        ImageIO.write(image, "JPEG", response.getOutputStream()); // 输出图片
    }

    /**
     * @param model 用于AOP
     * @return
     */
    @GetMapping("/register")
    public String register(Model model){
        return "register";
    }
}
