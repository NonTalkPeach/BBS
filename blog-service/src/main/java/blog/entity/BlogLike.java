package blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogLike {
    private int id;
    private String userCode;
    private int blogId;
    private Date likeDate;

    public BlogLike(String userCode, int blogId) {
        this.userCode = userCode;
        this.blogId = blogId;
    }
}
