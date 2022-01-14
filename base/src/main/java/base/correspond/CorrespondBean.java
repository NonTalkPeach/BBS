package base.correspond;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 各微服务之间通信Bean
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorrespondBean {
    public static final int SUCCESS = 200;
    public static final int FAIL = 500;
    /**
     * 调用返回状态码
     */
    private int code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

    public static CorrespondBean getSuccessBean() {
        return new CorrespondBean(SUCCESS,"",null);
    }

    public static CorrespondBean getSuccessBean(String message) {
        return new CorrespondBean(SUCCESS,message,null);
    }

    public static CorrespondBean getSuccessBean(String message, Object data) {
        return new CorrespondBean(SUCCESS,message,data);
    }

    public static CorrespondBean getFailBean() {
        return new CorrespondBean(FAIL,"",null);
    }

    public static CorrespondBean getFailBean(String message) {
        return new CorrespondBean(FAIL,message,null);
    }

    public static CorrespondBean getFailBean(String message, Object data) {
        return new CorrespondBean(FAIL,message,data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
