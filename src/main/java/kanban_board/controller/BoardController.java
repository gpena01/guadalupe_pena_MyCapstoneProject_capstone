package kanban_board.controller;

import kanban_board.models.Board;
import kanban_board.service.BoardService;
import kanban_board.user.User;
import kanban_board.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controller class handles incoming http requests
@Controller
public class BoardController {
    private final UserService userService;
    private final BoardService boardService;
    @Autowired
    public BoardController(BoardService boardService, UserService userService) {
        this.userService = userService;
        this.boardService = boardService;
    }
    @GetMapping("/listBoards")
    public String getAllBoards(Model model) {

        return "loggedin";
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
        UserDetails userPrincipal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userPrincipal.getUsername();
        User user = userService.getUser(username);
        List<Board> boards = user.getBoards();
        Board board = new Board("", user);
        boards.add(board);
        userService.saveUser(user);
        return "redirect:/home";
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
