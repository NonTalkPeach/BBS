package blog.service;

import blog.entity.BlogForView;
import blog.entity.CommentForView;

import java.util.List;

public interface BlogService {
    public int addOneBlog(String userCode, String title, String content);
    public int addOneComment(String userCode, int blogId, String content);
    public List<BlogForView> selectAllBlogsForView();
    public List<BlogForView> selectAllBlogsUniquelyForView(String userCode);
    public List<CommentForView> selectCommentsForView(int blogId);
    public int likeOneBlog(String userCode, int blogId);
    public int unlikeOneBlog(String userCode, int blogId);
}
