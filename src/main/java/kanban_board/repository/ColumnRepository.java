package kanban_board.repository;

import kanban_board.models.BoardColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnRepository extends JpaRepository<BoardColumn, Long> {
    BoardColumn findByColumnName(String columnName);
}
