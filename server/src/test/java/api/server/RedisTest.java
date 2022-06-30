package api.server;

import api.server.Redis.Token;
import api.server.Redis.TokenRedisRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class RedisTest {
    @Autowired
    private TokenRedisRepository repo;

    @Test
    void test() {
        Token token = new Token();
        Token token3 = null;
        token.setToken("testToken");
        token.setEmail("testEmail");
        // 저장
        repo.save(token);

        // `keyspace:id` 값을 가져옴
        Token token1 = repo.findByEmail(token.getEmail());

        assertEquals(token, token1);
        System.out.println(token + " || " + token1);
        // Person Entity 의 @RedisHash 에 정의되어 있는 keyspace (people) 에 속   한 키의 갯수를 구함
        repo.count();
    }
}