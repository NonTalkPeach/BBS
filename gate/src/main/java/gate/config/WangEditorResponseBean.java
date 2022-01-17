package gate.config;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class WangEditorResponseBean {
    String errno;
    List<String> data;
    public WangEditorResponseBean(String errno,List<String> data){
        this.errno=errno;
        this.data=data;
    }
    public WangEditorResponseBean(String errno,String data){
        this.errno=errno;
        this.data=new ArrayList<>();
        this.data.add(data);
    }
}
