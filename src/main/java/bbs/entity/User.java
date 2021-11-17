package bbs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userCode;
    private String email;
    private String username;
    private String passwd;
    private int level;
    private boolean activation;

    @Override
    public String toString() {
        return "User{" +
                "userCode='" + userCode + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", passwd='" + passwd + '\'' +
                ", level=" + level +
                ", activation=" + activation +
                '}';
    }
}
