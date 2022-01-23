package blog.service;

import blog.entity.BlogForView;

import java.util.List;

public interface BlogService {
    public int addOneBlog(String userCode, String title, String content);
    public List<BlogForView> selectAllBlogsForView();
    public int likeOneBlog(String userCode, int blogId);
    public int unlikeOneBlog(String userCode, int blogId);
}
