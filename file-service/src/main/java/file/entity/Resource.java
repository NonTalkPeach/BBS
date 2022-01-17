package file.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resource {
    private int id;
    private String fileName;
    private String fileDescription;
    private String ownerCode;
    private int downloadSum;
    private String timestamp;

    public Resource(String fileName, String ownerCode, String timestamp) {
        this.fileName = fileName;
        this.ownerCode = ownerCode;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", fileDescription='" + fileDescription + '\'' +
                ", ownerCode='" + ownerCode + '\'' +
                ", downloadSum=" + downloadSum +
                ", timestamp=" + timestamp +
                '}';
    }
}
