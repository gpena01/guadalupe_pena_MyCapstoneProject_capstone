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

}
