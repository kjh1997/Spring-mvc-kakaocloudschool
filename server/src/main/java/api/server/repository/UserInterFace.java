package api.server.repository;

import api.server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInterFace extends JpaRepository<User, Long> {
    User findByusername(String username);
}
