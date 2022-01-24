package blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private int id;
    private String userCode;
    private int blogId;
    private String content;
    private Date createDate;

    public Comment(String userCode, int blogId, String content) {
        this.userCode = userCode;
        this.blogId = blogId;
        this.content = content;
    }
}
