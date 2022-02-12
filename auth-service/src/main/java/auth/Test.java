package auth;

import auth.AuthServiceStarter;
import auth.utils.MailUtil;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest("AuthServiceStarter.class")
public class Test {
    @Autowired
    MailUtil mailUtil;

    @org.junit.Test
    public void test01() {
        mailUtil.sendCode("245149633@qq.com","test");
    }
}
