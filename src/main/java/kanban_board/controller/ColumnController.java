package kanban_board.controller;

import kanban_board.models.BoardColumn;
import kanban_board.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;

@Controller
public class ColumnController {
    private final ColumnService columnService;
    @Autowired
    public ColumnController(ColumnService columnService) {
        this.columnService = columnService;
    }
    @GetMapping("/createNewColumn")
    public String createNewColumn(Model model) {
        // create a new column
        BoardColumn boardColumn = new BoardColumn();
        model.addAttribute("boardColumn", boardColumn);
        return "home";
    }
    @GetMapping("/updateColumnName/{columnId}")
    public String updateColumnName(@PathVariable long columnId, Model model) {
        // update columnName
        BoardColumn boardColumn = columnService.getColumnById(columnId);
        model.addAttribute("boardColumn", boardColumn);
        return "home";
    }
    @PostMapping("/saveColumn")
    public String saveColumn(@ModelAttribute("boardColumn") BoardColumn boardColumn, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "home";
        }
        // save column to database
        columnService.saveColumn(boardColumn);
        return "redirect:/";
    }
    @GetMapping("/deleteColumn/{columnId}")
    public String deleteColumn(@PathVariable long columnId) {
        this.columnService.deleteColumnById(columnId);
        return "redirect:/";
    }

}
