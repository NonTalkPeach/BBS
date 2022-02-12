package gate;

import gate.redis.RedisOpsForString;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest("GateStarter.class")
public class Test {
    @Autowired
    RedisOpsForString redisOpsForString;

    @org.junit.Test
    public void test (){
        System.out.println(redisOpsForString.getString("asdasdafsdaf"));
    }
}
