package api.server.domain.repository;

import api.server.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardInterface extends JpaRepository<Board, Long> {
   Optional<List<Board>> findByContext(String context);

   Optional<List<Board>> findByTitle(String title);

}
