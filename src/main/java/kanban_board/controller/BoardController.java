package kanban_board.controller;

import kanban_board.models.Board;
import kanban_board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller class handles incoming http requests
@Controller
public class BoardController {
    private final BoardService boardService;
    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
    @GetMapping("/listBoards")
    public String getAllBoards(Model model) {
        model.addAttribute("listBoards", boardService.getAllBoards());
        return "home";
    }
    @GetMapping("/updateBoardTitle/{boardId}")
    public String updateBoardTitle(@PathVariable long boardId, Model model) {
        // update boardTitle
        Board board= boardService.getBoardById(boardId);
        model.addAttribute("board", board);
        return "home";
    }
    @GetMapping("/createNewBoard")
    public String createNewBoard(Model model) {
        // create a new board
        Board board = new Board();
        model.addAttribute("board", board);
        return "home";
    }
    @PostMapping("/saveBoard")
    public String saveBoard(@ModelAttribute("board") Board board, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "home";
        }
        // save board to database
        boardService.saveBoard(board);
        return "redirect:/";
    }
    @GetMapping("/deleteBoard/{boardId}")
    public String deleteBoard(@PathVariable long boardId) {
        this.boardService.deleteBoardById(boardId);
        return "redirect:/";
    }
}
