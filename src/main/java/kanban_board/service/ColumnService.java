package kanban_board.service;

import kanban_board.models.BoardColumn;
import kanban_board.repository.ColumnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColumnService {
    private final ColumnRepository columnRepository;
    @Autowired
    public ColumnService(ColumnRepository columnRepository) {
        this.columnRepository = columnRepository;
    }
    public BoardColumn saveColumn(BoardColumn column) {
        return columnRepository.save(column);
    }
    public List<BoardColumn> getAllColumns() {
        return columnRepository.findAll();
    }
    public BoardColumn getColumnById(long columnId) {
        Optional<BoardColumn> optionalColumn = columnRepository.findById(columnId);
        if (optionalColumn.isPresent()) {
            BoardColumn column = optionalColumn.get();
            return column;
        }
        throw new ColumnNotFoundException("Column not found with id: " + columnId);
    }
    public void deleteColumn(long columnId) {
        BoardColumn column = getColumnById(columnId);
        columnRepository.delete(column);
    }
    public BoardColumn updateColumn(long columnId, String newColumnName) {
        BoardColumn column = getColumnById(columnId);
        column.setColumnName(newColumnName);
        return columnRepository.save(column);
    }


    public BoardColumn createColumn(BoardColumn column) {
        BoardColumn createdColumn = columnRepository.save(column);
        return createdColumn;
    }
}
