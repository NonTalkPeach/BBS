import auth.utils.JWTTokenUtil;

public class test {
    public static void main(String[] args) {
        //System.out.println(JWTTokenUtil.getToken("wuyutao", "201925510124"));
        System.out.println(JWTTokenUtil.verifyToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2MzgyNDM5MTQsInVzZXJuYW1lIjoid3V5dXRhbyJ9.y8bBjwbGIVXvXwrgZY2sojo4Fdp3sr73aQJFlbZ_jMD39L91X8xUJs0eIpM0gHuTqnDGzaD8E89r0t4Q7q6mNQ","201925510124"));
    }
}
