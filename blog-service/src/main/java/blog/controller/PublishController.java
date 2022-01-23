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
public class PublishController {
    @Autowired
    BlogService blogService;

    @PostMapping("/publishBlog")
    public CorrespondBean publishBlog(String userCode, String title, String content) {
        if (userCode == null || title == null || content == null) return CorrespondBean.getFailBean("不允许有NULL值喔");
        blogService.addOneBlog(userCode,title,content);
        return CorrespondBean.getSuccessBean("发布成功");
    }
}
