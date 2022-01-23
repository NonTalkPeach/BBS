package blog.service.Impl;

import blog.dao.BlogMapper;
import blog.entity.Blog;
import blog.entity.BlogForView;
import blog.entity.BlogLike;
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
    public List<BlogForView> selectAllBlogsForView() {
        return blogMapper.selectAllBlogsForView();
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
