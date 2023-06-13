package kanban_board.service;

import kanban_board.models.Board;
import kanban_board.models.Column;
import kanban_board.repository.BoardRepository;
import kanban_board.repository.ColumnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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
    public Column saveColumn(Column column) {
        return columnRepository.save(column);
    }
    public List<Column> getAllColumns() {
        return columnRepository.findAll();
    }
    public Column getColumnById(long columnId) {
        Optional<Column> optionalColumn = columnRepository.findById(columnId);
        if (optionalColumn.isPresent()) {
            Column column = optionalColumn.get();
            return column;
        }
        throw new ColumnNotFoundException("Column not found with id: " + columnId);
    }
    public void deleteColumn(long columnId) {
        Column column = getColumnById(columnId);
        columnRepository.delete(column);
    }
    public Column updateColumn(long columnId, String newColumnName) {
        Column column = getColumnById(columnId);
        column.setColumnName(newColumnName);
        return columnRepository.save(column);
    }


    public Column createColumn(Column column) {
        Column createdColumn = columnRepository.save(column);
        return createdColumn;
    }
}
