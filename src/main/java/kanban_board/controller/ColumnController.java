package kanban_board.controller;

import kanban_board.models.Column;
import kanban_board.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/columns")
public class ColumnController {
    private final ColumnService columnService;
    @Autowired
    public ColumnController(ColumnService columnService) {
        this.columnService = columnService;
    }
    @PostMapping
    public ResponseEntity<Column> createColumn(@RequestBody Column column) {
        Column createdColumn = columnService.createColumn(column);
        return ResponseEntity.ok(createdColumn);
    }
    @GetMapping("/{columnId}")
    public ResponseEntity<Column> getColumn(@PathVariable long columnId) {
        Column column = columnService.getColumnById(columnId);
        return ResponseEntity.ok(column);
    }
    @PutMapping("/{columnId}")
    public ResponseEntity<Column> updateColumn(@PathVariable long columnId, @RequestBody Column updatedColumn) {
        Column column = columnService.updateColumn(columnId, updatedColumn.getColumnName());
        return ResponseEntity.ok(column);
    }
    @DeleteMapping("/{columnId}")
    public ResponseEntity<Void> deleteColumn(@PathVariable long columnId) {
        columnService.deleteColumn(columnId);
        return ResponseEntity.noContent().build();
    }
}
