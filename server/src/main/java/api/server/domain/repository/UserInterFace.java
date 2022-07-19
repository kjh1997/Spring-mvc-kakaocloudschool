package api.server.domain.repository;

import api.server.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserInterFace extends JpaRepository<User, Long> {
    User findByusername(String username);


    @Override
    @EntityGraph(attributePaths = {"board"})
    List<User> findAll();
}
