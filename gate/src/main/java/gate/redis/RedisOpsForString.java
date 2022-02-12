package gate.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisOpsForString {
    @Autowired
    @Qualifier("stringRedisTemplate")
    private StringRedisTemplate template;

    public void setString (String key, String value) {
        template.opsForValue().set(key,value);
    }

    /**
     * @param key
     * @param value
     * @param timeOut 秒级别
     */
    public void setString (String key, String value,long timeOut) {
        template.opsForValue().set(key,value,timeOut, TimeUnit.SECONDS);
    }

    public String getString (String key) {
        return template.opsForValue().get(key);
    }

    /**
     * 如果redis中不存在该key（默认value 为 0）， 则先创建该键，再进行加一
     * @param key
     */
    public String increase (String key) {
        if (getString(key) == null) setString(key, "0");
        template.opsForValue().increment(key);
        return getString(key);
    }
}
