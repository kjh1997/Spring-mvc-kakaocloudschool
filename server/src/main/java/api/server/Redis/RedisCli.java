package api.server.Redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisCli extends StringRedisTemplate {

    public StringRedisTemplate redisTemplate;

//    public void setRedis() {
//        redisTemplate.
//    }

}
