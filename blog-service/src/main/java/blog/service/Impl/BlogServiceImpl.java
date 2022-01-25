package blog.service.Impl;

import blog.dao.BlogMapper;
import blog.entity.*;
import blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogMapper blogMapper;

    @Override
    public int addOneBlog(String userCode, String title, String content) {
        Blog blog = new Blog(userCode, title, content);
        return blogMapper.insertOneBlog(blog);
    }

    @Override
    public int addOneComment(String userCode, int blogId, String content) {
        Comment comment = new Comment(userCode, blogId, content);
        return blogMapper.insertOneComment(comment);
    }

    @Override
    public BlogForView selectOneBlogByIdForView(int blogId) {
        return blogMapper.selectOneBlogByBlogIdForView(blogId);
    }

    @Override
    public BlogForView selectOneBlogByIdUniquelyForView(int blogId, String userCode) {
        return blogMapper.selectOneBlogByIdUniquelyForView(blogId,userCode);
    }

    @Override
    public List<BlogForView> selectAllBlogsForView() {
        return blogMapper.selectAllBlogsForView();
    }

    @Override
    public List<BlogForView> selectAllBlogsUniquelyForView(String userCode) {
        return blogMapper.selectAllBlogsUniquelyForView(userCode);
    }

    @Override
    public List<CommentForView> selectCommentsForView(int blogId) {
        return blogMapper.selectCommentsByBlogId(blogId);
    }

    @Override
    public int likeOneBlog(String userCode, int blogId) {
        return blogMapper.insertOneLike(new BlogLike(userCode,blogId));
    }

    @Override
    public int unlikeOneBlog(String userCode, int blogId) {
        return blogMapper.deleteOneLike(new BlogLike(userCode,blogId));
    }
}
