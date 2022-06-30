package api.server.Redis;

import org.springframework.data.repository.CrudRepository;

public interface TokenRedisRepository extends CrudRepository<Token, String> {
    Token findByEmail(String email);
}