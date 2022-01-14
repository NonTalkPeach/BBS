package file.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userCode;
    private String email;
    private String username;
    private String passwd;
    private int level;
    private String avatarUrl;

    public User(String username, String email, String passwd) {
        this.username = username;
        this.passwd = passwd;
        this.email = email;
        this.level = 0;
        this.userCode = UUID.randomUUID().toString();
        this.avatarUrl = "/avatarImg/defaultAvatar.jpg";
    }

    @Override
    public String toString() {
        return "User{" +
                "userCode='" + userCode + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", passwd='" + passwd + '\'' +
                ", level=" + level +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
