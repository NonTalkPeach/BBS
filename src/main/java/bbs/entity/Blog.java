package bbs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    private int id;
    private String userCode;
    private String title;
    private String content;
    private Date createDate;

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", userCode='" + userCode + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
