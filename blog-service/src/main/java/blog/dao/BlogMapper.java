package blog.dao;

import blog.entity.Blog;
import blog.entity.BlogForView;
import blog.entity.BlogLike;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogMapper {
    //单表操作
    public int insertOneBlog(Blog blog);
    public int insertOneLike(BlogLike blogLike);
    public int deleteOneLike(BlogLike blogLike);

    //联表查询
    public List<BlogForView> selectAllBlogsForView();

}
