package blog.controller;

import base.correspond.CorrespondBean;
import blog.dao.BlogMapper;
import blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/blog")
@ResponseBody
public class CommentAndLikeController {
    @Autowired
    BlogService blogService;

    @PostMapping("/likeBlog")
    public CorrespondBean likeBlog(String userCode, int blogId) {
        blogService.likeOneBlog(userCode, blogId);
        return CorrespondBean.getSuccessBean("点赞成功");
    }

    @PostMapping("/unLikeBlog")
    public CorrespondBean unLikeBlog(String userCode, int blogId) {
        int flag = blogService.unlikeOneBlog(userCode, blogId);
        if (flag > 0) {
            return CorrespondBean.getSuccessBean("取消点赞成功");
        }else return CorrespondBean.getFailBean("你已经取消点赞了");
    }
}
