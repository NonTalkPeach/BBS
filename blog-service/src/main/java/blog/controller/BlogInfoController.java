package blog.controller;

import base.correspond.CorrespondBean;
import blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/blog")
@ResponseBody
public class BlogInfoController {
    @Autowired
    private BlogService blogService;

    @RequestMapping("/getAllBlogs")
    public CorrespondBean getAllBlogs() {
        return CorrespondBean.getSuccessBean("获取Blog成功",blogService.selectAllBlogsForView());
    }


}
