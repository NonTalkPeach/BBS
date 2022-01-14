import auth.AuthServiceStarter;
import auth.redis.OpsForString;
import auth.utils.JWTTokenUtil;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)

@SpringBootTest(classes = AuthServiceStarter.class)
public class Test {
    @Autowired
    OpsForString opsForString;

    @org.junit.Test
    public void test01() {
        opsForString.setString("wuyutao","IQ190",20);
    }

    @org.junit.Test
    public void test02() {
        System.out.println(JWTTokenUtil.verifyToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJ1c2VySW5mbyI6eyJhdmF0YXJVcmwiOiIvYXZhdGFySW1nL2RlZmF1bHRBdmF0YXIuanBnIiwidXNlckNvZGUiOiI0MzNhYzBjZS0yZjM5LTQ0MTctOTMzZC1iMGQ2ZjRkZmU4OWEiLCJ1c2VybmFtZSI6IkFkbWluIn0sImV4cCI6MTY0MjA3MDMyN30.3_gO0KYIMlYUHloyFVStlZk69UjoZtKHPHZMYknYnQwXhf9lLGPTDj4TouiMuEpNnQa5VYqs8QsPfw2aK_NM2Q"));
    }
}
