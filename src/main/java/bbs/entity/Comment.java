package bbs.entity;

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
    private String content;
    private Date createDate;
    private int targetBlog;
    private int targetComment;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userCode='" + userCode + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", targetBlog=" + targetBlog +
                ", targetComment=" + targetComment +
                '}';
    }
}
