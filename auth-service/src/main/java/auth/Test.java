package auth;

import auth.AuthServiceStarter;
import auth.utils.MailUtil;
import auth.utils.PasswordUtil;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest("AuthServiceStarter.class")
public class Test {
    @Autowired
    MailUtil mailUtil;
    @Autowired
    PasswordUtil passwordUtil;
    @org.junit.Test
    public void test01() {
        System.out.println(passwordUtil.encode("123456"));
        System.out.println(passwordUtil.match("123456","$2a$10$XTfDNKCKZZpIyA5x4JiSNOwISjs4Qj94CJJeiJoluflBwR2apIR2O"));
    }
}
