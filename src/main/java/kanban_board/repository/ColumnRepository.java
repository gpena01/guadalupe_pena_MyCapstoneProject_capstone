package kanban_board.repository;

import kanban_board.models.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColumnRepository extends JpaRepository<Column, Long> {
    Column findByName(String columnName);

    List<Column> findByBoardId(Long boardId);
}
