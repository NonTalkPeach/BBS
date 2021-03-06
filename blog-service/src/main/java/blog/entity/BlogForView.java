package blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogForView {
    private int id;
    private String username;
    private String avatarUrl;
    private String title;
    private String content;
    private int likeSum;
    private boolean active;
    private Date createDate;
    private List<CommentForView> comments;
}
