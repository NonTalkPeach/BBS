package blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogForView {
    private int id;
    private String username;
    private String avatarUrl;
    private String title;
    private String content;
    private Date createDate;
}
