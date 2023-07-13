package kanban_board.controller;

import kanban_board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {
    private final BoardService boardService;
    @Autowired
    public HomeController(BoardService boardService) {
        this.boardService = boardService;
    }
    @GetMapping("/")
    public String home() { return "home"; }
    @GetMapping("/about")
    public String about() { return "about"; }
    @GetMapping("/contact")
    public String contact() { return "contact"; }
    @GetMapping("/home")
    public String loggedIn(Model model) {
        // When user is logged in, all pages should have sign out button
        boolean isLoggedIn = true;
        model.addAttribute("listBoards", boardService.getAllBoards());
        model.addAttribute("isLoggedIn", isLoggedIn);
        return "loggedin"; }

    @GetMapping("/my_boards")
    public String myBoards() {
        // "My Boards" tab appears when user is logged in
        return "my_boards"; }
}
