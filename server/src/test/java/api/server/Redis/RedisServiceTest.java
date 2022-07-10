package api.server.Redis;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

public class RedisServiceTest {

    private RedisService redisService;
    @Autowired
    public RedisServiceTest(RedisService redisService) {
        this.redisService = redisService;
    }

    @Test
    void test() {
        redisService.setValues("test1232","value");
        String test1232 = redisService.getValues("test1232");
        System.out.println(test1232);
    }
}