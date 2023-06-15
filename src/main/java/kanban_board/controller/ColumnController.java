package kanban_board.controller;

import kanban_board.models.BoardColumn;
import kanban_board.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/columns")
public class ColumnController {
    private final ColumnService columnService;
    @Autowired
    public ColumnController(ColumnService columnService) {
        this.columnService = columnService;
    }
    @PostMapping("/createColumn")
    public ResponseEntity<BoardColumn> createColumn(@RequestBody BoardColumn column) {
        BoardColumn createdColumn = columnService.createColumn(column);
        return ResponseEntity.ok(createdColumn);
    }
    @GetMapping("/{columnId}")
    public ResponseEntity<BoardColumn> getColumn(@PathVariable long columnId) {
        BoardColumn column = columnService.getColumnById(columnId);
        return ResponseEntity.ok(column);
    }
    @PutMapping("/{columnId}")
    public ResponseEntity<BoardColumn> updateColumn(@PathVariable long columnId, @RequestBody BoardColumn updatedColumn) {
        BoardColumn column = columnService.updateColumn(columnId, updatedColumn.getColumnName());
        return ResponseEntity.ok(column);
    }
    @DeleteMapping("/{columnId}")
    public ResponseEntity<Void> deleteColumn(@PathVariable long columnId) {
        columnService.deleteColumn(columnId);
        return ResponseEntity.noContent().build();
    }
}
