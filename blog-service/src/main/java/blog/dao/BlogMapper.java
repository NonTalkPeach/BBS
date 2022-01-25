package blog.dao;

import blog.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogMapper {
    //单表操作
    public int insertOneBlog(Blog blog);
    public int insertOneLike(BlogLike blogLike);
    public int deleteOneLike(BlogLike blogLike);
    public int insertOneComment(Comment comment);

    //联表查询
    public BlogForView selectOneBlogByBlogIdForView(int blogId);
    public BlogForView selectOneBlogByIdUniquelyForView(@Param("blogId")int blogId, @Param("userCode") String userCode);
    public List<BlogForView> selectAllBlogsForView();
    public List<BlogForView> selectAllBlogsUniquelyForView(String userCode);
    public List<CommentForView> selectCommentsByBlogId(int blogId);

}
