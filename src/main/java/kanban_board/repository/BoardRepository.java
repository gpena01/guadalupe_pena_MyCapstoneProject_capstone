package kanban_board.repository;

import kanban_board.models.Board;
import kanban_board.models.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByName(String boardTitle);
}
