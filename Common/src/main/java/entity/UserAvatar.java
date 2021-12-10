package auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAvatar {
    private String userCode;
    private String localName;

    @Override
    public String toString() {
        return "UserAvatar{" +
                "ownerEmail='" + userCode + '\'' +
                ", localName='" + localName + '\'' +
                '}';
    }
}
