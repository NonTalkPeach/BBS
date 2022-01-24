package blog.controller;

import base.correspond.CorrespondBean;
import blog.entity.BlogForView;
import blog.entity.CommentForView;
import blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/blog")
@ResponseBody
public class BlogInfoController {
    @Autowired
    private BlogService blogService;

    @RequestMapping("/getAllBlogs")
    public CorrespondBean getAllBlogs() {
        List<BlogForView> blogForViews = blogService.selectAllBlogsForView();
        for (BlogForView blogForView : blogForViews) {
            blogForView.setComments(blogService.selectCommentsForView(blogForView.getId()));
        }
        return CorrespondBean.getSuccessBean("获取Blog成功",blogForViews);
    }

    @PostMapping("/getAllBlogsUniquely")
    public CorrespondBean getAllBlogsUniquely(String userCode) {
        List<BlogForView> blogForViews = blogService.selectAllBlogsUniquelyForView(userCode);
        for (BlogForView blogForView : blogForViews) {
            blogForView.setComments(blogService.selectCommentsForView(blogForView.getId()));
        }
        return CorrespondBean.getSuccessBean("获取Blog成功",blogForViews);
    }
}
