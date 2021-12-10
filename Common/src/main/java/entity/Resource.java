package auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resource {
    private int id;
    private String fileName;
    private String fileDescription;
    private String localName;
    private int level;
    private String userCode;

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", fileDescription='" + fileDescription + '\'' +
                ", localName='" + localName + '\'' +
                ", level=" + level +
                ", author='" + userCode + '\'' +
                '}';
    }
}
