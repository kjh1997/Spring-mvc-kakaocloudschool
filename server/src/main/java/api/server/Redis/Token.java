package api.server.Redis;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;


@RedisHash(value = "token", timeToLive = 30)
@Data
public class Token {
    @Id
    private String id;
    private String email;
    private String token;
}