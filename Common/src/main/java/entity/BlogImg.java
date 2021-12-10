package auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogImg {
    private int blogId;
    private String localName;

    @Override
    public String toString() {
        return "BlogImg{" +
                "blogId=" + blogId +
                ", localName='" + localName + '\'' +
                '}';
    }
}
